import 'package:flutter/material.dart';
void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: ProfileScreen(),
    );
  }
}

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

  @override
 Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        elevation: 0,
        leading: IconButton(
          icon: const Icon(Icons.arrow_back, color: Colors.black),
          onPressed: () {
          },
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.edit, color: Colors.black),
            onPressed: () {
            },
          ),
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const CircleAvatar(
              radius: 80,
              backgroundImage: NetworkImage("https://scontent.fsgn5-15.fna.fbcdn.net/v/t39.30808-1/435702549_427665636614454_4294691182151656485_n.jpg?stp=dst-jpg_s200x200_tt6&_nc_cat=111&ccb=1-7&_nc_sid=e99d92&_nc_ohc=iPMI-dfITIQQ7kNvgGhQlC8&_nc_oc=Adjwxaly404mutIzEJuJEm7nE5_CIOZdVwgwv6ARBBLkdprjFPbEbohowpypySG6FVo&_nc_zt=24&_nc_ht=scontent.fsgn5-15.fna&_nc_gid=A29Hv4T41V5VwWtNQeNuOGH&oh=00_AYCpddMt5feenmuHwIC9yiu7E7kTCB14Wcwy9j3bCm1Kmg&oe=67CA670C"),
            ),
            const SizedBox(height: 16),
            const Text(
              "Yến Di",
              style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            const Text(
              "Bình Định, Việt Nam",
              style: TextStyle(fontSize: 20, color: Colors.grey),
            ),
          ],
        ),
      ),
    );
  }
}