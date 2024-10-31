package ctrl;

import utils.StateManager;

public class Main {
   static StateManager sm = new StateManager();
    public static void main(String[] args) {
        System.out.println(sm.readPlayers());
        sm.login("92f6977a-f1a0-4a74-b40f-c09c0d9e7d6f");
        System.out.println(StateManager.islogged);
        System.out.println(sm.getCurrentSession().getUsername());
    }
}
