import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'firebase_options.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'SmartTasks',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: LoginPage(),
    );
  }
}

class LoginPage extends StatefulWidget {
  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  Future<void> _signInWithGoogle() async {
    try {
      final GoogleSignInAccount? googleUser = await GoogleSignIn().signIn();

      if (googleUser == null) {
        return;
      }

      final GoogleSignInAuthentication googleAuth = await googleUser.authentication;

      final OAuthCredential credential = GoogleAuthProvider.credential(
        accessToken: googleAuth.accessToken,
        idToken: googleAuth.idToken,
      );

      final UserCredential userCredential = await FirebaseAuth.instance.signInWithCredential(credential);

      final User? user = userCredential.user;
      if (user != null) {
        print('Đăng nhập thành công: ${user.displayName} (${user.uid})');

        // Kiểm tra và lưu trữ dữ liệu người dùng trong Firestore (ví dụ)
        final usersCollection = FirebaseFirestore.instance.collection('users');
        final userDoc = await usersCollection.doc(user.uid).get();

        if (!userDoc.exists) {
          // Người dùng mới, lưu trữ thông tin cơ bản
          await usersCollection.doc(user.uid).set({
            'displayName': user.displayName,
            'email': user.email,
            'photoURL': user.photoURL,
            'createdAt': FieldValue.serverTimestamp(),
            // Thêm các trường mặc định khác nếu cần
          });
          print('Đã tạo tài liệu người dùng mới trong Firestore');
        } else {
          print('Đã tìm thấy tài liệu người dùng hiện có trong Firestore');
        }

        // Sau khi (có thể) lưu trữ dữ liệu, chuyển sang ProfileScreen
        Navigator.push( // Đã thay đổi thành Navigator.push
          context,
          MaterialPageRoute(builder: (context) => ProfileScreen()),
        );
      }
    } catch (e) {
      print('Lỗi đăng nhập Google: $e');
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Đã xảy ra lỗi khi đăng nhập bằng Google.')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    final screenHeight = MediaQuery.of(context).size.height;

    return Scaffold(
      body: Stack(
        children: <Widget>[
          Positioned(
            top: 0,
            left: 0,
            right: 0,
            height: screenHeight * 0.5,
            child: Image.asset(
              'assets/nen.png',
              fit: BoxFit.cover,
            ),
          ),
          Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                SizedBox(height: 50),
                Container(
                  width: 195,
                  height: 190,
                  decoration: BoxDecoration(
                    image: DecorationImage(
                      image: AssetImage('assets/logo_uth.png'),
                      fit: BoxFit.cover,
                    ),
                  ),
                ),
                SizedBox(height: 10),
                Text(
                  'SmartTasks',
                  style: TextStyle(fontSize: 24.0, fontWeight: FontWeight.bold, color: Colors.blue),
                ),
                Text(
                  'A simple and efficient to do app',
                  style: TextStyle(color: Colors.blue, fontSize: 12.0),
                ),
                SizedBox(height: 100),
                Text(
                  'Welcome',
                  style: TextStyle(fontSize: 16.0, fontWeight: FontWeight.bold),
                ),
                Text(
                  'Ready to explore? Log in to get started.',
                  textAlign: TextAlign.center,
                  style: TextStyle(fontSize: 14.0),
                ),
                SizedBox(height: 30),
                ElevatedButton.icon(
                  onPressed: _signInWithGoogle,
                  icon: Image.asset(
                    'assets/logo_google.png',
                    height: 24.0,
                  ),
                  label: Text(
                    'SIGN IN WITH GOOGLE',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  style: ElevatedButton.styleFrom(
                    foregroundColor: Colors.black87,
                    backgroundColor: const Color(0xFFD5EDFF),
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(9.0),
                      side: BorderSide(color: Colors.grey[300]!),
                    ),
                    padding: EdgeInsets.symmetric(vertical: 14.0, horizontal: 20),
                  ),
                ),
                Spacer(),
                Text(
                  '© UTHSmartTasks',
                  style: TextStyle(color: Colors.grey[500], fontSize: 12.0),
                ),
                SizedBox(height: 10),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class ProfileScreen extends StatefulWidget {
  @override
  _ProfileScreenState createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  User? _user;
  Map<String, dynamic>? _userData;
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    _loadUserData();
  }

  Future<void> _loadUserData() async {
    setState(() {
      _isLoading = true;
      _userData = null;
    });
    _user = _auth.currentUser;
    if (_user != null) {
      try {
        DocumentSnapshot userDoc = await _firestore.collection('users').doc(_user!.uid).get();
        if (userDoc.exists) {
          setState(() {
            _userData = userDoc.data() as Map<String, dynamic>;
            _isLoading = false;
          });
        } else {
          setState(() {
            _isLoading = false;
          });
          print('User data not found in Firestore');
          // Có thể điều hướng đến trang chỉnh sửa profile hoặc hiển thị thông báo
        }
      } catch (e) {
        setState(() {
          _isLoading = false;
        });
        print('Error loading user data: $e');
        // Xử lý lỗi (ví dụ: hiển thị thông báo cho người dùng)
      }
    } else {
      setState(() {
        _isLoading = false;
      });
      print('No user logged in');
      // Có thể điều hướng về trang đăng nhập
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (context) => LoginPage()),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: Row(
          children: [
            IconButton(
              icon: Icon(Icons.arrow_back),
              onPressed: () {
                Navigator.pop(context);
              },
            ),
            SizedBox(width: 8.0),
            Expanded(
              child: Text(
                'Profile',
                textAlign: TextAlign.center,
                style: TextStyle(fontWeight: FontWeight.bold, color:Colors.blue)
              ),
            ),
          ],
        ),
      ),
      body: _isLoading
          ? Center(child: CircularProgressIndicator())
          : SingleChildScrollView(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Center(
                    child: CircleAvatar(
                      radius: 60.0,
                      backgroundImage: _userData?['photoURL'] != null
                          ? NetworkImage(_userData!['photoURL']) as ImageProvider<Object>?
                          : AssetImage('assets/default_profile.png'),
                    ),
                  ),
                  SizedBox(height: 16.0),
                  Text(
                    'Name',
                    style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16.0),
                  ),
                  SizedBox(height: 8.0),
                  Container(
                    width: double.infinity,
                    padding: const EdgeInsets.all(12.0),
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.grey[300]!),
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                    child: Text(_userData?['displayName'] ?? 'N/A'),
                  ),
                  SizedBox(height: 16.0),
                  Text(
                    'Email',
                    style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16.0),
                  ),
                  SizedBox(height: 8.0),
                  Container(
                    width: double.infinity,
                    padding: const EdgeInsets.all(12.0),
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.grey[300]!),
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                    child: Text(_user?.email ?? 'N/A'),
                  ),
                  SizedBox(height: 16.0),
                  Text(
                    'Date of Birth',
                    style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16.0),
                  ),
                  SizedBox(height: 8.0),
                  Container(
                    width: double.infinity,
                    padding: const EdgeInsets.all(12.0),
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.grey[300]!),
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                    child: Text(_userData?['dob'] ?? 'N/A'),
                  ),
                  SizedBox(height: 50.0),
                  Center(
                    child: SizedBox(
                      width: 349,
                      height: 52,
                      child: ElevatedButton(
                        onPressed: () {
                          Navigator.pop(context);
                        },
                        style: ElevatedButton.styleFrom(
                          backgroundColor: Colors.blue, // Đặt màu nền thành xanh
                          foregroundColor: Colors.white, // Đặt màu chữ thành trắng để tương phản
                        ),
                        child: Text('Back'),
                      ),
                    ),
                  ),
                  SizedBox(height: 16.0),
                ],
              ),
            ),
    );
  }
}