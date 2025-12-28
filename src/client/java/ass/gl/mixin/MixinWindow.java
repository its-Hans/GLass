package ass.gl.mixin;

import ass.gl.Util;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Window.class)
public class MixinWindow {

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwWindowHint(II)V", remap = false), index = 1)
    private int gl_ass$reHint(int hint, int value) {
        return switch (hint) {
            case GLFW.GLFW_CONTEXT_VERSION_MAJOR -> Util.VERSION.major();
            case GLFW.GLFW_CONTEXT_VERSION_MINOR -> Util.VERSION.minor();
            
            default -> value;
        };
    }
}
