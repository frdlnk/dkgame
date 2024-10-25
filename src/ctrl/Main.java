package ctrl;

import utils.StateManager;

public class Main {
   static StateManager sm = new StateManager();
    public static void main(String[] args) {
        sm.login("c94cb914-1e18-417a-a65b-cc2cba3aaa0c");
        System.out.println(StateManager.islogged);
        System.out.println(sm.getCurrentSession());

        // sm.logout();
        // System.out.println(StateManager.islogged);
        // System.out.println(sm.getCurrentSession());
    }
}
