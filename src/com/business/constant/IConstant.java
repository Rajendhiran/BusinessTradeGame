package com.business.constant;

import com.business.game.R;


/* Author: Rajendhiran 
   Date  : May 29, 2013 
   Time  : 3:16:31 PM */

public interface IConstant
{
	// Player ID
	int _UserPlayer_ID=1;
	int _SystemPlayer_ID=2;

	//Dice Details
	int _TotalDiceCount=6;
	int _TotalDice=2;

	//Playability Time Details. 
	int _PlayDelayForSystem=7500;
	int _rollAnimations = 5;
	int _delayTime = 5;

	// Resource Reference 
	int _HouseSelected= R.drawable.house;
	int _HouseUnSelected = R.drawable.house_unselect;

	int _HotelSelected= R.drawable.hotel;
	int _HotelUnSelected = R.drawable.hotel_unselect;

	int _CoinsUserPlayer = R.drawable.gold_soldier;
	int _CoinsSystemPlayer = R.drawable.black_soldier;

	int _FlagUserPlayer = R.drawable.green_flag;
	int _FlagSystemPlayer = R.drawable.red_flag;

	// Dice Images Reference
	int[] _diceImages = new int[] { R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6 };
}
