import ATM.ATMDemo;
import CarRentalSystem.CarRentalSystemDemo;
import MeeetingScheduler.MeetingRoomDemo;
import jiradesign.JiraDemo;
import logginframework.LoggerDemo;
import lrucache.LRUCacheDemo;
import pubsub.PubSubDemo;
import vendingmachine.VendingMachineClient;

public class Design {
    public static void main(String[] args) {
        // VendingMachineClient vmClient = new VendingMachineClient();
        // vmClient.runVendingMachine();
        // LoggerDemo ld = new LoggerDemo();
        // ld.initializeLogger();
        // JiraDemo demo = new JiraDemo();
        // demo.initalizeJira();
        // ATMDemo demo = new ATMDemo();
        // demo.initializeATM();
        // LRUCacheDemo demo = new LRUCacheDemo();
        // demo.initalizeLRUCache();
        // PubSubDemo demo = new PubSubDemo();
        // demo.initializePubSub();
        // CarRentalSystemDemo demo = new CarRentalSystemDemo();
        // demo.initializeCarRental();
        MeetingRoomDemo demo = new MeetingRoomDemo();
        demo.startMeetingRoomInitializer();
    }
}
