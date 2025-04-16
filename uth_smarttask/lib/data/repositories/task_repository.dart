import '../database/task_dao.dart';
import '../../models/task.dart';

class TaskRepository {
  final TaskDao _taskDao;

  TaskRepository(this._taskDao);

  Future<List<Task>> getTasks() => _taskDao.getAllTasks();

  Future<void> addTask(Task task) => _taskDao.insertTask(task);

  Future<void> updateTask(Task task) => _taskDao.updateTask(task);

  Future<void> deleteTask(Task task) => _taskDao.deleteTask(task);
}
