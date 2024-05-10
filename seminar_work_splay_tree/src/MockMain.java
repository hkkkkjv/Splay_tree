public class MockMain {
    public static void main(String[] args) {
        SplayTree splayTree = new SplayTree();
        int[] array = {4, 2, 6, 1, 3, 5, 7, 8, 9, 10};
        for (int value : array){
            splayTree.insert(value);
        }
        System.out.println(splayTree.search(7).getKey());//7
        splayTree.delete(4);
        System.out.println(splayTree.search(4)==null);//true
    }
}
