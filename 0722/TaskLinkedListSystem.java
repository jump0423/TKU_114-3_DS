public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        taskList.addNormal("T1", "撰寫報告");
        taskList.addNormal("T2", "整理資料");
        taskList.addUrgent("T3", "緊急修復");
        taskList.addNormal("T4", "回覆信件");

        System.out.println("工作總數：" + taskList.size());

        System.out.println("完成 T1：" + taskList.complete("T1"));
        System.out.println("完成 T99：" + taskList.complete("T99"));

        System.out.println("未完成工作：");
        taskList.printUnfinished();
        System.out.println("未完成數量：" + taskList.countUnfinished());

        System.out.println("刪除 T4：" + taskList.remove("T4"));
        System.out.println("工作總數：" + taskList.size());

        System.out.println("未完成工作：");
        taskList.printUnfinished();
        System.out.println("未完成數量：" + taskList.countUnfinished());
    }
}
