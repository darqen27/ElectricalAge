package mods.eln.gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class GuiVerticalWorkingZoneBar extends Gui implements IGuiObject{

    public int width,height,xPosition,yPosition;
	private GuiHelper helper;


	public GuiVerticalWorkingZoneBar(int xPosition,int yPosition,int width,int height,GuiHelper helper) 
	{
		this.width = width;
		this.height = height;
		this.xPosition = xPosition;
		this.yPosition = yPosition;	
		this.helper = helper;	
	}
	
	
	public void setValue(float value)
	{
		this.value = value;
	}
	
	public void setMinMax(float min,float max)
	{
		this.min = min;
		this.max = max;
	}
	float min,max;
	float value;
	
	class Zone{
		public Zone(float height,int color) {
			this.color = color;
			this.height = height;
		}
		public int color;
		public float height;
	}
	
	ArrayList<Zone> zoneList = new ArrayList<GuiVerticalWorkingZoneBar.Zone>();
	
	
	public void addZone(float height,int color)
	{
		zoneList.add(new Zone(height,color));
	}
	
	@Override
	public void idraw(int x, int y, float f) {
		drawRect(xPosition, yPosition-2,xPosition + width,yPosition + height+2,0xFF404040);
		drawRect(xPosition+1, yPosition-1,xPosition + width-1,yPosition + height+1,0xFF606060);
		drawRect(xPosition+2, yPosition,xPosition + width-2,yPosition + height,0xFF808080);
		
		float factorY = 0f;
		
		for(Zone zone : zoneList){
			drawRect(xPosition+2, getYFromFactor(factorY + zone.height),xPosition + width-2,getYFromFactor(factorY),zone.color);
				
			factorY += zone.height;
		}
		
		drawBare(f, x, y);

	}
	public void drawBare(float par1, int x, int y)
	{
		int cursorPos = getYFromFactor(value);
		drawRect(xPosition - 2, cursorPos-2,xPosition + width + 2,cursorPos + 2,0xFF202020);
		drawRect(xPosition - 1, cursorPos-1,xPosition + width + 1,cursorPos + 1,0xFF606060);
 
	}
	
	int getYFromFactor(float factorY)
	{
		return (int) (-(factorY - min)/(max-min)*height) + yPosition + height;
	}



	@Override
	public boolean ikeyTyped(char key, int code) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void imouseClicked(int x, int y, int code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void imouseMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void imouseMovedOrUp(int x, int y, int witch) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void translate(int x, int y) {
		this.xPosition += x;
		this.yPosition += y;
	}
	
	
	ArrayList<String> comment = new ArrayList<String>();
	
	public void setComment(String[] comment)
	{
		this.comment.clear();
		for(String str : comment)
		{
			this.comment.add(str);
		}
	}
	
	public void setComment(int line,String comment)
	{
		if(this.comment.size() < line + 1)
			this.comment.add(line, comment);
		else
			this.comment.set(line, comment);
	}
	
	@Override
	public void idraw2(int x, int y) {
		// TODO Auto-generated method stub
		if(/*visible == true && */(x >= xPosition && y >= yPosition && x < xPosition + width && y < yPosition + height))
		{
			int px,py;
			px = xPosition - helper.getHoveringTextWidth(comment,Minecraft.getMinecraft().fontRenderer)/2;
			py = yPosition + height + 20/* - helper.getHoveringTextHeight(comment,Minecraft.getMinecraft().fontRenderer)*/;
			helper.drawHoveringText(comment, px, py, Minecraft.getMinecraft().fontRenderer);
		}
				
	}

}