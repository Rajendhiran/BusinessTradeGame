package com.business.game;

import com.business.bean.GameBean;
import com.business.constant.IConstant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

// Author: Rajendhiran May 28, 2013 3:14:36 PM

public class BusinessGameBoard extends Activity
{
	public EditText dummyEditText,dummyEditText2;
	public Button dummyClick,dummyClick2;
	public int UserDiceValue,SysteDiceValue;
	public int Player_No=0; //Player_No   0- User  1 - System.
	public GameBean _UserGame,_SystemGame;
	public ImageView Flag,House1,House2,House3,Hotel,Player;
	public int CoinReference,PreviousReferenceUser,PreviousReferenceSystem;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games_board);
		init();
		process();
	}

	/* Author: Rajendhiran 
	   Date  : May 29, 2013 
	   Time  : 11:09:44 AM */
	private void process() 
	{
		dummyClick.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				UserDiceValue+=Integer.parseInt(dummyEditText.getText().toString().trim());
				Toast.makeText(getApplicationContext(),"Dummy Click User: "+UserDiceValue,20).show();
				PlayGameBoard(UserDiceValue,IConstant._UserPlayer_ID,_UserGame,IConstant._CoinsUserPlayer);				
			}
		});

		dummyClick2.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				SysteDiceValue+=Integer.parseInt(dummyEditText2.getText().toString().trim());
				Toast.makeText(getApplicationContext(),"Dummy Click System: "+UserDiceValue,20).show();
				PlayGameBoard(SysteDiceValue,IConstant._SystemPlayer_ID,_SystemGame,IConstant._CoinsSystemPlayer);
			}
		});
	}

	/* Author: Rajendhiran 
	   Date  : May 29, 2013 
	   Time  : 11:09:03 AM */
	private void init() 
	{
		// Dummy Widgets UserPlayer Reference.
		dummyEditText = (EditText) findViewById(R.id.TestInput);
		dummyClick = (Button) findViewById(R.id.TestDice);

		// Dummy Widgets SystemPlayer Reference.
		dummyEditText2 = (EditText) findViewById(R.id.TestInput2);
		dummyClick2 = (Button) findViewById(R.id.TestDice2);

		_UserGame = new GameBean();
		_SystemGame = new GameBean();
	}

	protected void onPause() 
	{
		super.onPause();
	}

	protected void onResume() 
	{
		super.onResume();
	}

	private void PlayGameBoard(int diceValue, int player_No, GameBean Game, int PlayerIcon) 
	{
		if(diceValue>36)
			diceValue-=36;
		int PreviousValue=Game.getPreviousPosition();		
		int CurrentValue = diceValue;		
		Game.setPreviousPosition(CurrentValue);
		Game.setCurrentPosition(diceValue);
		
		switch(diceValue)
		{
		case 1:
		{
			// Game Coins Placement for Start
			if(player_No==IConstant._UserPlayer_ID)
			{
				CoinReference =R.id.StartPlayer1;								
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
				PreviousReferenceUser=CoinReference;
			}
			else
			{
				CoinReference =R.id.StartPlayer2;				
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
				PreviousReferenceSystem=CoinReference;
			}
			Toast.makeText(getApplicationContext(), "start", 10).show();
		}
		break;
		case 2:
		{
			// Game Coins Placement for Bombay City
			if(player_No==IConstant._UserPlayer_ID)
			{
				CoinReference =R.id.BombayPlayer1;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
				PreviousReferenceUser=CoinReference;
			}
			else
			{
				CoinReference =R.id.BombayPlayer2;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
				PreviousReferenceSystem=CoinReference;
			}
			Toast.makeText(getApplicationContext(), "Bombay", 10).show();
		}
		break;
		case 3:
		{
			// Game Coins Placement for Gold Site
			if(player_No==IConstant._UserPlayer_ID)
			{
				CoinReference =R.id.GoldMinePlayer1;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
				PreviousReferenceUser=CoinReference;
			}
			else
			{
				CoinReference =R.id.GoldMinePlayer2;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
				PreviousReferenceSystem=CoinReference;
			}			
			Toast.makeText(getApplicationContext(), "Gold", 10).show();
		}
		break;
		case 4:
		{
			// Game Coins Placement for Ahemadabad City
			if(player_No==IConstant._UserPlayer_ID)
			{
				CoinReference =R.id.AhmedabadPlayer1;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
				PreviousReferenceUser=CoinReference;
			}
			else
			{
				CoinReference =R.id.AhmedabadPlayer2;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
				PreviousReferenceSystem=CoinReference;
			}
			Toast.makeText(getApplicationContext(), "Ahemadabad", 10).show();
		}		
		break;
		case 5:
		{
			// Game Coins Placement for Railway Site
			if(player_No==IConstant._UserPlayer_ID)
			{
				CoinReference =R.id.RailwayPlayer1;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
				PreviousReferenceUser=CoinReference;
			}
			else
			{
				CoinReference =R.id.RailwayPlayer2;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
				PreviousReferenceSystem=CoinReference;
			}
			Toast.makeText(getApplicationContext(), "Railway", 10).show();
		}		
		break;
		case 6:
		{
			// Game Coins Placement for Income Tax
			if(player_No==IConstant._UserPlayer_ID)
			{
				CoinReference =R.id.IncomeTaxPlayer1;				
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
				PreviousReferenceUser=CoinReference;
			}
			else
			{
				CoinReference =R.id.IncomeTaxPlayer2;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
				PreviousReferenceSystem=CoinReference;
			}
			Toast.makeText(getApplicationContext(), "Income Tax", 10).show();
		}		
		break;
		case 7:
		{
			// Game Coins Placement for Indore City
			if(player_No==IConstant._UserPlayer_ID)
			{
				CoinReference =R.id.IndorePlayer1;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
				PreviousReferenceUser=CoinReference;
			}
			else
			{
				CoinReference =R.id.IndorePlayer2;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
				PreviousReferenceSystem=CoinReference;
			}
			Toast.makeText(getApplicationContext(), "Indore", 10).show();
		}		
		break;
		case 8:
		{
			// Game Coins Placement for Chance
			if(player_No==IConstant._UserPlayer_ID)
			{
				CoinReference =R.id.ChancePlayer1;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
				PreviousReferenceUser=CoinReference;
			}
			else
			{
				CoinReference =R.id.ChancePlayer2;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
				PreviousReferenceSystem=CoinReference;
			}
			Toast.makeText(getApplicationContext(), "Chance", 10).show();
		}		
		break;
		case 9:
		{
			// Game Coins Placement for Jaipur City 
			if(player_No==IConstant._UserPlayer_ID)
			{
				CoinReference =R.id.JaipurPlayer1;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
				PreviousReferenceUser=CoinReference;
			}
			else
			{
				CoinReference =R.id.JaipurPlayer2;
				SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
				PreviousReferenceSystem=CoinReference;
			}
			Toast.makeText(getApplicationContext(), "Jaipur", 10).show();
		}		
		break;
		/*case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 30:
		case 31:
		case 32:
		case 33:
		case 34:
		case 35:
		case 36:*/
		default:
		{
			UserDiceValue=0;
			SysteDiceValue=0;
		}
		}
	}

	/* Author: Rajendhiran 
	   Date  : May 29, 2013 
	   Time  : 4:42:06 PM */
	private void SetCoins(int previousValue, int currentdiceValue, int player_No,int playerIcon, int coinReference, int previousReference) 
	{
		if(!(previousReference<1))
		{
			Player = (ImageView) findViewById(previousReference);
			Player.setBackgroundResource(0);		 
			Toast.makeText(getApplicationContext(), "Null Player", 10).show();
		}

		Player = (ImageView) findViewById(coinReference);
		Player.setBackgroundResource(playerIcon);
		Toast.makeText(getApplicationContext(), "Player Image", 10).show();		
	}
}