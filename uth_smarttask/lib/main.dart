import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'views/task_list_screen.dart';
import 'view_models/task_list_view_model.dart';
import 'data/database/app_database.dart';
import 'data/repositories/task_repository.dart';
void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final database = await $FloorAppDatabase.databaseBuilder('uth_task_db.db').build();
  final repository = TaskRepository(database.taskDao);

  runApp(
    ChangeNotifierProvider(
      create: (_) => TaskViewModel(repository),
      child: const MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'UTH Smart Task',
      home: TaskListScreen(),

    );
  }
}