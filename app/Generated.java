import org.jooq.util.GenerationTool;

/**
 * Created by zg on 2016/6/27.
 */
public class Generated {

    public static void main(String[] args) {
        try {
            GenerationTool.main(new String[]{"library.xml"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
