import 'package:floor/floor.dart';

@entity
class Task {
  @PrimaryKey(autoGenerate: true)
  final int? id; // Thêm id làm khóa chính, có thể null nếu tự động tăng

  final String title;
  final String description;

  Task({
    this.id, // Id có thể null khi tạo mới task
    required this.title,
    required this.description,
  });
}