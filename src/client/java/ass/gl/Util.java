package ass.gl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Util {
    public static final Logger LOG = LoggerFactory.getLogger("GLass");
    public static final Path cfgPath = Path.of("config/gl.ass");
    public static final GLVersion VERSION;
    
    static {
        LOG.info("{}", VERSION = initGLVersion());
    }

    private static GLVersion initGLVersion() {
        if (Files.exists(cfgPath)) {
            try {
                var lines = Files.readAllLines(cfgPath);

                return GLVersion.fromList(lines);
            } catch (IOException e) {
                LOG.error("Unexpected IOException while reading config", e);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                LOG.error("Invalid config, example:" +
                        "\n4" +
                        "\n1");
                LOG.error("", e);
            }
        } else try {
            LOG.info("no config exists, trying to create...");
            Files.write(cfgPath, GLVersion.DEFAULT.toList());
        } catch (IOException e) {
            LOG.error("Unexpected IOException while creating config", e);
        }

        return GLVersion.DEFAULT;
    }
}
