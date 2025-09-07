package mypals.ml.mixin;
import com.terraformersmc.modmenu.gui.ModsScreen;
import com.terraformersmc.modmenu.gui.widget.ModListWidget;
import mypals.ml.KeepMyScroll;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModsScreen.class)
public abstract class ModScreenMixin extends Screen {
	@Shadow private ModListWidget modList;


	protected ModScreenMixin(Text title) {
		super(title);
	}

	@Override
	public void removed() {
		KeepMyScroll.scrollAmount = this.modList.getScrollAmount();
		super.removed();
	}
	@Inject(method = "init", at = @At("TAIL"))
	private void initTail(CallbackInfo ci) {
		this.modList.setScrollAmount(KeepMyScroll.scrollAmount);
	}
}