package ctrl;

import utils.StateManager;

public class Main {
   static StateManager sm = new StateManager();
    public static void main(String[] args) {
        sm.readPlayers();
        sm.login("bb6e57dc-1800-4112-9c90-06baba6e4f3c");
        System.out.println(StateManager.currentPlayerOnSession.getUsername());
    }
}
