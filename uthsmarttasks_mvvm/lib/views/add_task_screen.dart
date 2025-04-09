import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../view_models/task_list_view_model.dart';
import '../models/task.dart';

class AddTaskScreen extends StatefulWidget {
  const AddTaskScreen({super.key});

  @override
  State<AddTaskScreen> createState() => _AddTaskScreenState();
}

class _AddTaskScreenState extends State<AddTaskScreen> {
  final TextEditingController _titleController = TextEditingController();
  final TextEditingController _descriptionController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Center(
          child: Text(
            'Add New',
            style: TextStyle(
              color: Colors.blue,
              fontWeight: FontWeight.bold,
            ),
          ),
        ),
        leading: IconButton(
          icon: Image.asset(
            'assets/back_arrow.png',
            width: 40,
            height: 40,
          ),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
        backgroundColor: Colors.white,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              'Task',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 16,
              ),
            ),
            const SizedBox(height: 8),
            TextField(
              controller: _titleController,
              decoration: const InputDecoration(
                hintText: 'Do homework',
                border: OutlineInputBorder(),
              ),
            ),
            const SizedBox(height: 16),
            const Text(
              'Description',
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 16,
              ),
            ),
            const SizedBox(height: 8),
            TextField(
              controller: _descriptionController,
              maxLines: 3,
              decoration: const InputDecoration(
                hintText: "Don't give up",
                border: OutlineInputBorder(),
              ),
            ),
            const SizedBox(height: 24),
            
            Align( // Sử dụng Align để căn chỉnh nút
              alignment: Alignment.center, // Căn giữa nút theo chiều ngang
              child: SizedBox( // Sử dụng SizedBox để giới hạn kích thước nút
                width: 150.0, // Điều chỉnh chiều rộng mong muốn
                height: 40.0, // Điều chỉnh chiều cao mong muốn
                child: ElevatedButton(
                  onPressed: () {
                    if (_titleController.text.isNotEmpty) {
                      final newTask = Task(
                        title: _titleController.text,
                        description: _descriptionController.text,
                      );
                      Provider.of<TaskListViewModel>(context, listen: false).addTask(newTask);
                      Navigator.pop(context); // Quay lại màn hình danh sách
                    } else {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(content: Text('Vui lòng nhập tiêu đề công việc')),
                      );
                    }
                  },
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.cyan,
                    foregroundColor: Colors.white,
                  ),
                  child: const Text('Add'), 
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}