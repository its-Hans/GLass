package ass.gl;

import java.util.List;

public record GLVersion(int major, int minor) {
    static GLVersion DEFAULT = new GLVersion(4, 1);

    static GLVersion fromList(List<String> cfg) throws NumberFormatException, IndexOutOfBoundsException {
        int major = Integer.parseInt(cfg.get(0));
        int minor = Integer.parseInt(cfg.get(1));

        return new GLVersion(major, minor);
    }

    List<String> toList() {
        return List.of(
                String.valueOf(major),
                String.valueOf(minor)
        );
    }
}
