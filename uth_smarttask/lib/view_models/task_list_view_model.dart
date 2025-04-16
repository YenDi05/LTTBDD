// viewmodels/task_viewmodel.dart
import 'package:flutter/material.dart';
import '../models/task.dart';
import '../data/repositories/task_repository.dart';

class TaskViewModel extends ChangeNotifier {
  final TaskRepository _repository;

  TaskViewModel(this._repository) {
    loadTasks();
  }

  List<Task> _tasks = [];

  List<Task> get tasks => _tasks;

  Future<void> loadTasks() async {
    _tasks = await _repository.getTasks();
    notifyListeners();
  }

  Future<void> addTask(Task task) async {
    await _repository.addTask(task);
    await loadTasks();
  }

  Future<void> toggleTaskStatus(Task task) async {
    final updated = Task(
      id: task.id,
      title: task.title,
      description: task.description,
    );
    await _repository.updateTask(updated);
    await loadTasks();
  }

  Future<void> deleteTask(Task task) async {
    await _repository.deleteTask(task);
    await loadTasks();
  }
}
