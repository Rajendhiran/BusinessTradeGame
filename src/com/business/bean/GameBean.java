package com.business.bean;

import java.util.ArrayList;
public class GameBean
{
	int PreviousPosition;
	int CurrentPosition;
	double CashInHand;
	public ArrayList<Assets> PlayersAssets;

	public GameBean()
	{
		PreviousPosition=0;
		CurrentPosition=0;
		CashInHand=0.00f;
		PlayersAssets=new ArrayList<GameBean.Assets>();
	}

	public int getPreviousPosition() {
		return PreviousPosition;
	}

	public void setPreviousPosition(int previousPosition) {
		PreviousPosition = previousPosition;
	}

	public int getCurrentPosition() {
		return CurrentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		CurrentPosition = currentPosition;
	}

	public double getCashInHand() {
		return CashInHand;
	}

	public void setCashInHand(double cashInHand) {
		CashInHand = cashInHand;
	}

	public ArrayList<Assets> getPlayersAssets() {
		return PlayersAssets;
	}

	public void setPlayersAssets(ArrayList<Assets> playersAssets) {
		PlayersAssets = playersAssets;
	}

	public class Assets
	{
		int CapturedCityID;
		int HouseCount;
		boolean isHotel;
		
		Assets()
		{
			CapturedCityID=0;
			HouseCount=0;
			isHotel=false;
		}

		public int getCapturedCityID() {
			return CapturedCityID;
		}

		public void setCapturedCityID(int capturedCityID) {
			CapturedCityID = capturedCityID;
		}

		public int getHouseCount() {
			return HouseCount;
		}

		public void setHouseCount(int houseCount) {
			HouseCount = houseCount;
		}

		public boolean isHotel() {
			return isHotel;
		}

		public void setHotel(boolean isHotel) {
			this.isHotel = isHotel;
		}
	}
}
