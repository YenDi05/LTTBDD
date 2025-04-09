import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../view_models/task_list_view_model.dart';
import '../widgets/task_item.dart';
import 'add_task_screen.dart';
import '../models/task.dart';

class TaskListScreen extends StatelessWidget {
  const TaskListScreen({super.key});

  final List<MaterialColor> _cardColors = const <MaterialColor> [
    Colors.pink,
    Colors.blue,
    Colors.brown,
    Colors.pink,
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        leading: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Image.asset(
            'assets/back_arrow.png',
            width: 40,
            height: 40,
          ), 
          
        ),
        title: const Center( // Sử dụng Center widget để căn giữa
          child: Text(
            'List',
            style: TextStyle(
              color: Colors.blue,
              fontWeight: FontWeight.bold, // Thêm fontWeight để in đậm
            ),
          ),
        ),
        actions: [
          Padding(
            padding: const EdgeInsets.only(right: 16.0),
            child: InkWell(
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const AddTaskScreen()),
                );
              },
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Image.asset(
                  'assets/add_icon.png',
                  width: 42, // Điều chỉnh kích thước icon nếu cần
                  height: 42, // Điều chỉnh kích thước icon nếu cần
                ),
              ),
            ),
          ),
        ],
      ),
      body: Consumer<TaskListViewModel>(
        builder: (context, viewModel, child) {
          return ListView.builder(
            itemCount: viewModel.tasks.length,
            itemBuilder: (context, index) {
              final task = viewModel.tasks[index];
              // Lấy màu từ danh sách _cardColors dựa trên index
              final itemColor = _cardColors[index % _cardColors.length].shade100;
              return TaskItem(task: task, backgroundColor: itemColor);
            },
          );
        },
      ),
      bottomNavigationBar: SizedBox( // Sử dụng SizedBox để đặt kích thước cố định
        height: 60.0, // Đặt chiều cao mong muốn là 60.0
        child: BottomNavigationBar(
         type: BottomNavigationBarType.fixed,
          items: [
            BottomNavigationBarItem(
              icon: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Image.asset(
                  'assets/home_icon.png',
                  width: 24, 
                  height: 24,
                  ), // Thay thế bằng đường dẫn ảnh của bạn
              ),
              label: '',
            ),
            BottomNavigationBarItem(
              icon: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Image.asset(
                  'assets/calendar_icon.png',
                  width: 24, 
                  height: 24, 
                  ), // Thay thế bằng đường dẫn ảnh của bạn
              ),
              label: '',
            ),
            BottomNavigationBarItem(
              icon: Transform.translate(
                offset: const Offset(0, -50),
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Image.asset(
                    'assets/add_circle_icon.png', 
                    width: 90, 
                    height: 90), 
                ),
              ),
              label: '',
            ),
            BottomNavigationBarItem(
              icon: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Image.asset(
                  'assets/list_icon.png',
                  width: 24, 
                  height: 24,
                ),
              ),
              label: '',
            ),
            BottomNavigationBarItem(
              icon: Padding(
                padding: const EdgeInsets.all(8.0),
                child: Image.asset(
                  'assets/settings_icon.png',
                  width: 24, 
                  height: 24,
                  ), // Thay thế bằng đường dẫn ảnh của bạn
              ),
              label: '',
            ),
          ],
        ),
      ),
    );
  }
}