import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../view_models/task_list_view_model.dart';
import '../widgets/task_item.dart';
import 'add_task_screen.dart';

class TaskListScreen extends StatefulWidget {
  const TaskListScreen({super.key});

  @override
  State<TaskListScreen> createState() => _TaskListScreenState();
}

class _TaskListScreenState extends State<TaskListScreen> {
  final List<MaterialColor> _cardColors = const <MaterialColor>[
    Colors.pink,
    Colors.blue,
    Colors.brown,
    Colors.pink,
  ];

  @override
  void initState() {
    super.initState();
    // Gọi loadTasks sau khi widget được tạo
    WidgetsBinding.instance.addPostFrameCallback((_) {
      Provider.of<TaskViewModel>(context, listen: false).loadTasks();
    });
  }

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
        title: const Center(
          child: Text(
            'List',
            style: TextStyle(
              color: Colors.blue,
              fontWeight: FontWeight.bold,
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
                  width: 42,
                  height: 42,
                ),
              ),
            ),
          ),
        ],
      ),
      body: Consumer<TaskViewModel>(
        builder: (context, viewModel, child) {
          if (viewModel.tasks.isEmpty) {
            return const Center(child: Text('Không có công việc nào.'));
          }
          return ListView.builder(
            itemCount: viewModel.tasks.length,
            itemBuilder: (context, index) {
              final task = viewModel.tasks[index];
              final itemColor = _cardColors[index % _cardColors.length].shade100;
              return TaskItem(task: task, backgroundColor: itemColor);
            },
          );
        },
      ),
      bottomNavigationBar: SizedBox(
        height: 60.0,
        child: BottomNavigationBar(
          type: BottomNavigationBarType.fixed,
          items: [
            _buildBottomItem('assets/home_icon.png'),
            _buildBottomItem('assets/calendar_icon.png'),
            BottomNavigationBarItem(
              icon: Transform.translate(
                offset: const Offset(0, -50),
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Image.asset(
                    'assets/add_circle_icon.png',
                    width: 90,
                    height: 90,
                  ),
                ),
              ),
              label: '',
            ),
            _buildBottomItem('assets/list_icon.png'),
            _buildBottomItem('assets/settings_icon.png'),
          ],
        ),
      ),
    );
  }

  BottomNavigationBarItem _buildBottomItem(String assetPath) {
    return BottomNavigationBarItem(
      icon: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Image.asset(
          assetPath,
          width: 24,
          height: 24,
        ),
      ),
      label: '',
    );
  }
}
