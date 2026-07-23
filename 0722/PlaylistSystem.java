public class PlaylistSystem {
    public static void main(String[] args) {
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("加入 S1：" + playlist.addLast("S1", "First Song"));
        System.out.println("加入 S2：" + playlist.addLast("S2", "Second Song"));
        System.out.println("加入 S3：" + playlist.addLast("S3", "Third Song"));
        System.out.println("重複加入 S1：" + playlist.addLast("S1", "Duplicate Song"));

        playlist.printAll();
        System.out.println("size：" + playlist.size());

        PlaylistNode found = playlist.search("S2");
        if (found != null) {
            System.out.println("搜尋 S2：" + found.songName);
        } else {
            System.out.println("搜尋 S2：找不到");
        }

        System.out.println("刪除第一首 S1：" + playlist.remove("S1"));
        playlist.printAll();

        System.out.println("刪除最後一首 S3：" + playlist.remove("S3"));
        playlist.printAll();

        System.out.println("刪除不存在的 S99：" + playlist.remove("S99"));
        System.out.println("size：" + playlist.size());
    }
}
