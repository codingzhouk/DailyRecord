import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZkCreate {
    // create static instance for zookeeper class.
    private static ZooKeeper zk;

    // create static instance for ZooKeeperConnection class.
    private static ZkConnection conn;

    // Method to create znode in zookeeper ensemble
    public static void create(String path, byte[] data) throws
            KeeperException,InterruptedException {
        /**
         * 参数
         * path：表示路径
         * data：表示节点的保存数据
         * ACL：权限类型
         * mode：表示节点的类型（永久类型、临时类型、顺序节点）
         */
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
    }

    public static void main(String[] args) {

        // znode path
        String path = "/MyFirstZnode/test2"; // Assign path to znode

        // data in byte array
        byte[] data = "My first zookeeper app".getBytes(); // Declare data

        try {
            conn = new ZkConnection();
            zk = conn.connect("10.3.71.100");
            create(path, data); // Create the data to the specified path
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); //Catch error message
        }
    }
}
