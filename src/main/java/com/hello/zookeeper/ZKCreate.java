package com.hello.zookeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

public class ZKCreate {
   // create static instance for zookeeper class.
   private static ZooKeeper zk;

   // create static instance for ZooKeeperConnection class.
   private static ZooKeeperConnection conn;
   
   static final CountDownLatch connectedSignal = new CountDownLatch(1);

   // Method to create znode in zookeeper ensemble
   public static void create(String path, byte[] data) throws 
      KeeperException,InterruptedException {
      zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE,
      CreateMode.PERSISTENT);
   }
   
   public static void main(String[] args) {

      // znode path
      String path = "/MyFirstZnode/bbbb"; // Assign path to znode

      // data in byte array
      byte[] data = "My first zookeeper app".getBytes(); // Declare data
		
      try {
         conn = new ZooKeeperConnection();
         zk = conn.connect("localhost");
//         create(path, data); // Create the data to the specified path
         
//         Stat stat = zk.exists(path, true);
//         System.out.println("版本："+stat.getVersion());
         
//         byte[] responseData = zk.getData(path, new Watcher(){
         
//			@Override
//			public void process(WatchedEvent watchedevent) {
//				// TODO Auto-generated method stub
//				if(watchedevent.getType().equals(Event.EventType.None)){
//					if(watchedevent.getState().equals(KeeperState.Expired)){
//						connectedSignal.countDown();
//					}
//				}else{
//					try {
//						byte[] watchResponse = zk.getData(path,false,null);
//						System.out.println("watchResponse:"+new String(watchResponse,"UTF-8"));
//						connectedSignal.countDown();
//					} catch (Exception e) {
//					}
//				}
//			}
//        	 
//         }, null);
//         
//         System.out.println("responseData:"+new String(responseData,"UTF-8"));
//         connectedSignal.await();
//         conn.close();
         
//         zk.setData(path, data,zk.exists(path, false).getVersion());
         
         
//         List<String> zNodes = zk.getChildren(path, new Watcher(){
//
//			@Override
//			public void process(WatchedEvent watchedevent) {
//				System.out.println("watchedevent:"+watchedevent);
//				try {
//					System.out.println(zk.getChildren(path,false));
//				} catch (Exception e) {
//					
//				}
//				connectedSignal.countDown();
//			}
//        	 
//         });
//         
//         System.out.println(zNodes);
//         connectedSignal.await();
         
         zk.delete(path,zk.exists(path, false).getVersion());
      } catch (Exception e) {
         System.out.println(e.getMessage()); //Catch error message
      }
   }
}