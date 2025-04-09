import 'package:flutter/material.dart';
import '../models/task.dart';
import 'package:flutter/foundation.dart';

class TaskListViewModel extends ChangeNotifier {
  final List<Task> _tasks = [
    Task(
      title: 'Complete Android Project',
      description: 'Finish the UI, integrate API, and write documentation',
    ),
    Task(
      title: 'Complete Android Project',
      description: 'Finish the UI, integrate API, and write documentation',
    ),
    Task(
      title: 'Complete Android Project',
      description: 'Finish the UI, integrate API, and write documentation',
    ),
    Task(
      title: 'Complete Android Project',
      description: 'Finish the UI, integrate API, and write documentation',
    ),
  ];

  List<Task> get tasks => _tasks;

  // Phương thức để thêm một công việc mới
  void addTask(Task newTask) {
    _tasks.add(newTask);
    notifyListeners(); // Thông báo cho các Widget lắng nghe rằng dữ liệu đã thay đổi
  }
}