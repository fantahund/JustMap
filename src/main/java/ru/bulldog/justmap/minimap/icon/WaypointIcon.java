package ru.bulldog.justmap.minimap.icon;

import ru.bulldog.justmap.client.JustMapClient;
import ru.bulldog.justmap.client.config.ClientParams;
import ru.bulldog.justmap.minimap.Minimap;
import ru.bulldog.justmap.minimap.waypoint.Waypoint;
import ru.bulldog.justmap.util.MathUtil;

public class WaypointIcon extends MapIcon<WaypointIcon> {
	private Waypoint waypoint;
	public WaypointIcon(Minimap map, Waypoint waypoint) {
		super(map);
		this.waypoint = waypoint;
	}

	@Override
	public void draw(int mapX, int mapY, float rotation) {
		int size = 8;
		
		double drawX = mapX + x - size / 2;
		double drawY = mapY + y - size / 2;
		
		int mapSize = JustMapClient.MAP.getMapSize();
		if (ClientParams.rotateMap) {
			double centerX = mapX + mapSize / 2;
			double centerY = mapY + mapSize / 2;
			
			rotation = MathUtil.correctAngle(rotation) + 180;
			
			double angle = Math.toRadians(-rotation);
			
			double posX = (int) (centerX + (drawX - centerX) * Math.cos(angle) - (drawY - centerY) * Math.sin(angle));
			double posY = (int) (centerY + (drawY - centerY) * Math.cos(angle) + (drawX - centerX) * Math.sin(angle));
			
			drawX = posX;
			drawY = posY;
		}
		
		if (drawX < mapX || drawX > (mapX + mapSize) ||
			drawY < mapY || drawY > (mapY + mapSize)) return;

		Waypoint.Icon icon = waypoint.getIcon();
		if (icon != null) {
			icon.draw(drawX, drawY, size);
		}			
	}
	
	public boolean isHidden() {
		return waypoint.hidden;
	}
}