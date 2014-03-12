package org.eclipse.e4.renderers.custom.renderers;

import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.renderers.swt.StackRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;

public class CustomStackRenderer extends StackRenderer {

	private CTabFolder ctf;
	private Font oldFont;
	private Color oldBgColor;
	
	@Override
	public Object createWidget(MUIElement element, Object parent) {
	
		CTabFolder ctf = (CTabFolder) super.createWidget(element, parent);
//		ctf.setSimple(false);
		return ctf;
	}
	
	@Override
	protected void updateTab(CTabItem cti, MPart part, String attName,
			Object newValue) {
		super.updateTab(cti, part, attName, newValue);
		if (part.isDirty()){
			cti.setText(""+part.getLocalizedLabel()+"");
			oldFont = cti.getFont();
			Device device = cti.getFont().getDevice();
			cti.setFont(new Font(device, "Calibri", 10,
					SWT.ITALIC));
			oldBgColor = cti.getControl().getBackground();
			cti.getControl().setBackground(new Color(device, 0,0,0));
			ctf = cti.getParent();
			CTabItem cTabItem = new CTabItem(ctf , ctf.getItemCount());
			cTabItem.setText("<-- changes pending");
			ctf.setSimple(false);
			
		}
		else {
			ctf.getItem(ctf.getItemCount()-1).dispose();
			cti.setFont(oldFont);
			cti.getControl().setBackground(oldBgColor);
			cti.setText(part.getLocalizedLabel());
			ctf.setSimple(true);
			
		}
	}

}
