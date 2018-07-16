import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;


public class ZkConnection {

    // declare zookeeper instance to access ZooKeeper ensemble
    private ZooKeeper zoo;
    /**
     * CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。
     *
     * 计数器初始值为线程的数量。当每一个线程完成自己任务后，计数器的值就会减一。
     * 当计数器的值为0时，表示所有的线程都已经完成了任务，然后在CountDownLatch上等待的线程就可以恢复执行任务。
     */
    final CountDownLatch connectedSignal = new CountDownLatch(1);

    // Method to connect zookeeper ensemble.
    public ZooKeeper connect(String host) throws IOException, InterruptedException {

        zoo = new ZooKeeper(host, 5000, new Watcher() {

            public void process(WatchedEvent we) {
                //连接成功
                if (we.getState() == Event.KeeperState.SyncConnected) {
                    connectedSignal.countDown();
                }
            }
        });
        //直到它的所有线程执行完毕
        connectedSignal.await();
        return zoo;
    }

    // Method to disconnect from zookeeper server
    public void close() throws InterruptedException {
        zoo.close();
    }
}
