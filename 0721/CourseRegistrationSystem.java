import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<CourseHomework> courses = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("1. 新增課程  2. 選課  3. 退選  4. 刪除  5. 搜尋  6. 統計  7. 離開");
            System.out.print("請選擇：");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("代碼：");
                    String code = scanner.nextLine();
                    System.out.print("名稱：");
                    String name = scanner.nextLine();
                    System.out.print("容量：");
                    int capacity = scanner.nextInt();
                    scanner.nextLine();
                    addCourse(courses, code, name, capacity);
                    break;
                case 2:
                    System.out.print("請輸入要選課的代碼：");
                    String enrollCode = scanner.nextLine();
                    enrollCourse(courses, enrollCode);
                    break;
                case 3:
                    System.out.print("請輸入要退選的代碼：");
                    String withdrawCode = scanner.nextLine();
                    withdrawCourse(courses, withdrawCode);
                    break;
                case 4:
                    System.out.print("請輸入要刪除的代碼：");
                    String deleteCode = scanner.nextLine();
                    deleteCourse(courses, deleteCode);
                    break;
                case 5:
                    System.out.print("請輸入要搜尋的代碼：");
                    String searchCode = scanner.nextLine();
                    CourseHomework found = findCourse(courses, searchCode);
                    if (found == null) {
                        System.out.println("找不到此課程。");
                    } else {
                        System.out.println(found);
                    }
                    break;
                case 6:
                    printStatistics(courses);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("無效選項。");
            }
        }

        scanner.close();
    }

    public static void addCourse(ArrayList<CourseHomework> courses, String code, String name, int capacity) {
        if (findCourse(courses, code) != null) {
            System.out.println("代碼重複，新增失敗。");
            return;
        }
        courses.add(new CourseHomework(code, name, capacity));
        System.out.println("新增成功。");
    }

    public static CourseHomework findCourse(ArrayList<CourseHomework> courses, String code) {
        for (CourseHomework course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return course;
            }
        }
        return null;
    }

    public static void enrollCourse(ArrayList<CourseHomework> courses, String code) {
        CourseHomework found = findCourse(courses, code);
        if (found == null) {
            System.out.println("找不到此課程。");
            return;
        }
        if (!found.enroll()) {
            System.out.println("課程已額滿，選課失敗。");
        } else {
            System.out.println("選課成功。");
        }
    }

    public static void withdrawCourse(ArrayList<CourseHomework> courses, String code) {
        CourseHomework found = findCourse(courses, code);
        if (found == null) {
            System.out.println("找不到此課程。");
            return;
        }
        if (!found.withdraw()) {
            System.out.println("目前沒有人選課，退選失敗。");
        } else {
            System.out.println("退選成功。");
        }
    }

    public static void deleteCourse(ArrayList<CourseHomework> courses, String code) {
        CourseHomework found = findCourse(courses, code);
        if (found == null) {
            System.out.println("找不到此課程，刪除失敗。");
            return;
        }
        courses.remove(found);
        System.out.println("刪除成功。");
    }

    public static void printStatistics(ArrayList<CourseHomework> courses) {
        int totalCourses = courses.size();
        int totalEnrollments = 0;
        int fullCourses = 0;

        for (CourseHomework course : courses) {
            totalEnrollments += course.getCurrentCount();
            if (course.isFull()) {
                fullCourses++;
            }
        }

        System.out.println("總課程數：" + totalCourses);
        System.out.println("總選課人次：" + totalEnrollments);
        System.out.println("額滿課程數：" + fullCourses);
    }
}
