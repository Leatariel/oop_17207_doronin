package ru.nsu.ccfit.doronin.lab5.Client;

public class ClientLauncher {

    public static String ipAddr = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        Controller controller = new Controller();
        ClientGUI gui = new ClientGUI(controller);
        Client client = new Client(ipAddr, port, controller);
        controller.setClient(client);
        controller.setGui(gui);
        gui.setVisible(true);
    }
}
