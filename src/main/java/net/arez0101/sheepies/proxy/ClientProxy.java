package net.arez0101.sheepies.proxy;

import org.cyclops.cyclopscore.init.ModBase;
import org.cyclops.cyclopscore.proxy.ClientProxyComponent;
import org.cyclops.cyclopscore.proxy.CommonProxyComponent;

import net.arez0101.sheepies.Sheepies;

public class ClientProxy extends ClientProxyComponent {

	public ClientProxy() {
		super(new CommonProxy());
	}
	
	@Override
	public ModBase getMod() {
		return Sheepies.INSTANCE;
	}
}
