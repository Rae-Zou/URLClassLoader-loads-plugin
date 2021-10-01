public class Plugin2 implements PluginInterface{
    @Override
    public String getName() {
        return "Plugin2";
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public String getAddress() {
        return "3x Cuba St, Wellington";
    }
}
