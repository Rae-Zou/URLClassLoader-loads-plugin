public class Plugin1 implements PluginInterface {

    @Override
    public String getName() {
        return "Plugin1";
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getAddress() {
        return "2x Webb st, Wellington";
    }
}
