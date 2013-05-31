package com.business.game;

import com.business.bean.GameBean;
import com.business.constant.IConstant;

import java.io.IOException;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

// Author: Rajendhiran May 28, 2013 3:14:36 PM

@SuppressLint("HandlerLeak")
public class BusinessGameBoard extends Activity
{
	private Resources res;
	private Drawable dice[];
	private final Random randomGen = new Random();

	private int diceSum=0;
	private int roll[];
	private int diceAns=0;		
	private int diceAnsSystem=0;
	
	private ImageView die1;
	private ImageView die2;
	
	private Handler animationHandler;	
	private Handler systemPlayHandler;
	
	private Runnable r;	
	
	private Button RollTheDice;
	private Button dummyClick,dummyClick2;
	private RelativeLayout ManualLayout,DiceLayout;
	private EditText dummyEditText,dummyEditText2;
	
	private boolean flag = false;
	private boolean Flag_Play=true;
	
	private int UserDiceValue,SysteDiceValue;
	private int Player_No=0; //Player_No   0- User  1 - System.
	private GameBean _UserGame,_SystemGame;
	private ImageView Flag,House1,House2,House3,Hotel,Player;
	private int CoinReference,PreviousReferenceUser,PreviousReferenceSystem;
	
	protected void onCreate(Bundle savedInstanceState) 
	{		
		//paused=false;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.games_board); 
		try
		{
			init();
			process();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
				if(UserDiceValue>36)
					UserDiceValue-=36;
				//Toast.makeText(getApplicationContext(),"Dummy Click User: "+UserDiceValue,20).show();				
				PlayGameBoard(UserDiceValue,IConstant._UserPlayer_ID,_UserGame,IConstant._CoinsUserPlayer);				
			}
		});

		dummyClick2.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				SysteDiceValue+=Integer.parseInt(dummyEditText2.getText().toString().trim());
				if(SysteDiceValue>36)
					SysteDiceValue-=36;
				//Toast.makeText(getApplicationContext(),"Dummy Click System: "+UserDiceValue,20).show();
				PlayGameBoard(SysteDiceValue,IConstant._SystemPlayer_ID,_SystemGame,IConstant._CoinsSystemPlayer);
			}
		});

		RollTheDice.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				try 
				{
					//rollDice();					
					RollDiceTask UserTaskRollDice = new RollDiceTask();
					UserTaskRollDice.execute();
					RollTheDice.setVisibility(Button.GONE);
					
				} 
				catch (Exception e) 
				{
					Log.d("Exception RolltheDiceBtn: ", ""+e);	
				}					
			}
		});

		animationHandler = new Handler() 
		{
			public void handleMessage(Message msg) 
			{
				die1.setImageDrawable(dice[roll[0]]);
				die2.setImageDrawable(dice[roll[1]]);
			}
		};
		
		r = new Runnable() 
		{
			public void run() 
			{
				SystemStartPlay();
			}
		};
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
		
		ManualLayout = (RelativeLayout) findViewById(R.id.ManualLayout);
		ManualLayout.setVisibility(RelativeLayout.GONE);
		
		DiceLayout = (RelativeLayout) findViewById(R.id.DiceLayout);
		//DiceLayout.setVisibility(RelativeLayout.GONE);
		
		_UserGame = new GameBean();
		_SystemGame = new GameBean();
		
		systemPlayHandler = new Handler();		

		die1 = (ImageView) findViewById(R.id.die1);
		die2 = (ImageView) findViewById(R.id.die2);
		RollTheDice = (Button) findViewById(R.id.Roll);
		
		roll = new int[IConstant._TotalDice] ;
		dice = new Drawable[IConstant._TotalDiceCount];
		
		res = getResources();
		for (int i = 0; i < IConstant._TotalDiceCount; i++) 
			dice[i] = res.getDrawable(IConstant._diceImages[i]);

	}

	protected void onPause() 
	{
		super.onPause();
		//paused=false;
	}

	protected void onResume() 
	{
		super.onResume();
		//paused=true;
	}

	private void PlayGameBoard(int diceValue, int player_No, GameBean Game, int PlayerIcon) 
	{		
		Toast.makeText(getApplicationContext(), "Dice Value: PB "+diceValue, Toast.LENGTH_LONG).show();
		
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
				CoinReference =R.id.StartPlayer1;								
			else
				CoinReference =R.id.StartPlayer2;				
			//Toast.makeText(getApplicationContext(), "start", 10).show();
		}
		break;

		case 2:
		{
			// Game Coins Placement for Bombay City
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.BombayPlayer1;
			else
				CoinReference =R.id.BombayPlayer2;

			//Toast.makeText(getApplicationContext(), "Bombay", 10).show();
		}
		break;

		case 3:
		{
			// Game Coins Placement for Gold Site
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.GoldMinePlayer1;
			else
				CoinReference =R.id.GoldMinePlayer2;

			//Toast.makeText(getApplicationContext(), "Gold", 10).show();
		}
		break;

		case 4:
		{
			// Game Coins Placement for Ahemadabad City
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.AhmedabadPlayer1;
			else
				CoinReference =R.id.AhmedabadPlayer2;
			//Toast.makeText(getApplicationContext(), "Ahemadabad", 10).show();
		}		
		break;

		case 5:
		{
			// Game Coins Placement for Railway Site
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.RailwayPlayer1;
			else
				CoinReference =R.id.RailwayPlayer2;

			//Toast.makeText(getApplicationContext(), "Railway", 10).show();
		}		
		break;

		case 6:
		{
			// Game Coins Placement for Income Tax
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.IncomeTaxPlayer1;				
			else
				CoinReference =R.id.IncomeTaxPlayer2;

			//Toast.makeText(getApplicationContext(), "Income Tax", 10).show();
		}		
		break;

		case 7:
		{
			// Game Coins Placement for Indore City
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.IndorePlayer1;
			else
				CoinReference =R.id.IndorePlayer2;

			//Toast.makeText(getApplicationContext(), "Indore", 10).show();
		}		
		break;

		case 8:
		{
			// Game Coins Placement for Chance
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.ChancePlayer1;
			else
				CoinReference =R.id.ChancePlayer2;
			//Toast.makeText(getApplicationContext(), "Chance", 10).show();
		}		
		break;

		case 9:
		{
			// Game Coins Placement for Jaipur City 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.JaipurPlayer1;
			else
				CoinReference =R.id.JaipurPlayer2;
			//Toast.makeText(getApplicationContext(), "Jaipur", 10).show();
		}		
		break;

		case 10:
		{
			// Game Coins Placement for Jail Punishment not to Play the Next Round and Penalty of Rs. 1000 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.JailPlayer1;
			else
				CoinReference =R.id.JailPlayer2;
			//Toast.makeText(getApplicationContext(), "Jail", 10).show();
		}
		break;

		case 11:
		{
			// Game Coins Placement for Delhi City
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.DelhiPlayer1;
			else
				CoinReference =R.id.DelhiPlayer2;
		}
		break;

		case 12:
		{
			// Game Coins Placement for Factory Site 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.FactoryPlayer1;
			else
				CoinReference =R.id.FactoryPlayer2;
		}
		break;

		case 13:
		{
			// Game Coins Placement for Chandigarh City 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.ChandigarhPlayer1;
			else
				CoinReference =R.id.ChandigarhPlayer2;
		}
		break;

		case 14:
		{
			// Game Coins Placement for Bus Site
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.BusPlayer1;
			else
				CoinReference =R.id.BusPlayer2;
		}
		break;

		case 15:
		{
			// Game Coins Placement for Service 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.CCPlayer1;
			else
				CoinReference =R.id.CCPlayer2;
		}
		break;

		case 16:
		{
			// Game Coins Placement for Simla City 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.SimlaPlayer1;
			else
				CoinReference =R.id.SimlaPlayer2;
		}
		break;

		case 17:
		{
			// Game Coins Placement for Amristar 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.AmristarPlayer1;
			else
				CoinReference =R.id.AmristarPlayer2;
		}
		break;

		case 18:
		{
			// Game Coins Placement for Srinagar 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.SrinagarPlayer1;
			else
				CoinReference =R.id.SrinagarPlayer2;
		}
		break;

		case 19:
		{
			// Game Coins Placement for Club 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.ClubPlayer1;
			else
				CoinReference =R.id.ClubPlayer2;
		}
		break;

		case 20:
		{
			// Game Coins Placement for Agra City 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.AgraPlayer1;
			else
				CoinReference =R.id.AgraPlayer2;
		}
		break;

		case 21:
		{
			// Game Coins Placement for Chance 2 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.Chance_1_Player1;
			else
				CoinReference =R.id.Chance_1_Player2;
		}
		break;

		case 22:
		{
			// Game Coins Placement for Kanpur City 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.KanpurPlayer1;
			else
				CoinReference =R.id.KanpurPlayer2;
		}
		break;

		case 23:
		{
			// Game Coins Placement for Darjeeling 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.DarjeelingPlayer1;
			else
				CoinReference =R.id.DarjeelingPlayer2;
		}
		break;

		case 24:
		{
			// Game Coins Placement for Kolkatta City
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.KolkataPlayer1;
			else
				CoinReference =R.id.KolkataPlayer2;
		}
		break;

		case 25:
		{
			// Game Coins Placement for Patna City 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.PatnaPlayer1;
			else
				CoinReference =R.id.PatnaPlayer2;
		}
		break;

		case 26:
		{
			// Game Coins Placement for Air India Site 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.AirindiaPlayer1;
			else
				CoinReference =R.id.AirindiaPlayer2;
		}
		break;

		case 27:
		{
			// Game Coins Placement for Hydreabad City 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.HydreabadPlayer1;
			else
				CoinReference =R.id.HydreabadPlayer2;
		}
		break;

		case 28:
		{
			// Game Coins Placement for Rest House
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.ResHousePlayer1;
			else
				CoinReference =R.id.ResHousePlayer2;
		}
		break;

		case 29:
		{
			// Game Coins Placement for Chennai City 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.ChennaiPlayer1;
			else
				CoinReference =R.id.ChennaiPlayer2;
		}
		break;

		case 30:
		{
			// Game Coins Placement for Service  
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.CC1Player1;
			else
				CoinReference =R.id.CC1Player2;
		}
		break;

		case 31:
		{
			// Game Coins Placement for Ooty City 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.OotyPlayer1;
			else
				CoinReference =R.id.OotyPlayer2;
		}
		break;

		case 32:
		{
			// Game Coins Placement for Cochin
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.CochinPlayer1;
			else
				CoinReference =R.id.CochinPlayer2;
		}
		break;

		case 33:
		{
			// Game Coins Placement for Bangalore 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.BangalorePlayer1;
			else
				CoinReference =R.id.BangalorePlayer2;
		}
		break;

		case 34:
		{
			// Game Coins Placement for Shipping Site
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.ShippingPlayer1;
			else
				CoinReference =R.id.ShippingPlayer2;
		}
		break;
		case 35:
		{
			// Game Coins Placement for Margo City
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.margoPlayer1;
			else
				CoinReference =R.id.margoPlayer2;
		}
		break;

		case 36:
		{
			// Game Coins Placement for Sales Tax 
			if(player_No==IConstant._UserPlayer_ID)
				CoinReference =R.id.SalesTaxPlayer1;
			else
				CoinReference =R.id.SalesTaxPlayer2;
		}
		break;
		}

		if(player_No==IConstant._UserPlayer_ID)
		{		
			SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceUser);
			PreviousReferenceUser=CoinReference;
		}
		else
		{		
			SetCoins(PreviousValue,diceValue,player_No,PlayerIcon,CoinReference,PreviousReferenceSystem);
			PreviousReferenceSystem=CoinReference;
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
			//Toast.makeText(getApplicationContext(), "Null Player", 10).show();
		}

		Player = (ImageView) findViewById(coinReference);
		Player.setBackgroundResource(playerIcon);
		//Toast.makeText(getApplicationContext(), "Player Image", 10).show();	
		systemPlayHandler.postDelayed(r, IConstant._PlayDelayForSystem);
	}
	
	private void SystemStartPlay()
	{
		Flag_Play=false;
		RollDiceTask SystemTaskRollDice = new RollDiceTask();
		SystemTaskRollDice.execute();
	}
	class RollDiceTask extends AsyncTask<Void,Void, Boolean> 
	{
		protected void onPreExecute() 
		{
			super.onPreExecute();
		}

		protected Boolean doInBackground(Void... params) 
		{			
			rollDice();
			return flag;
		}

		protected void onPostExecute(Boolean result) 
		{		
			super.onPostExecute(result);   
			if((result) && Flag_Play)
			{
				Toast.makeText(BusinessGameBoard.this, "User Roll: "+diceSum, Toast.LENGTH_LONG).show();
				diceAns+=diceSum;
				if(diceAns>36)
					diceAns-=36;				
				PlayGameBoard(diceAns,IConstant._UserPlayer_ID,_UserGame,IConstant._CoinsUserPlayer);				
			}
			else if((result) && Flag_Play==false)
			{
				Toast.makeText(BusinessGameBoard.this, "System Roll: "+diceSum, Toast.LENGTH_LONG).show();
				diceAnsSystem+=diceSum;
				if(diceAnsSystem>36)
					diceAnsSystem-=36;				
				PlayGameBoard(diceAnsSystem,IConstant._SystemPlayer_ID,_SystemGame,IConstant._CoinsSystemPlayer);	
				Flag_Play=true;
				RollTheDice.setVisibility(Button.VISIBLE);
				systemPlayHandler.removeCallbacks(r);
			}
		}
	}
	
	private void rollDice() 
	{
		//if (paused) return;
		new Thread(new Runnable() 
		{
			public void run() 
			{				
				//for (int i = 0; i < IConstant._rollAnimations; i++) 
				//{
					doRoll();
				//}
			}
		}).start();

		MediaPlayer mp = MediaPlayer.create(this, R.raw.roll);
		try
		{
			mp.prepare();
		} 
		catch (IllegalStateException e) 
		{
			e.printStackTrace(); 
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		mp.start();
	}

	private void doRoll() 
	{ 	
		// only does a single roll
		roll[0] = randomGen.nextInt(6);
		roll[1] = randomGen.nextInt(6);

		diceSum = roll[0] + roll[1]+2; // 2 is added because the values of the rolls start with 0 not 1	
		synchronized (getLayoutInflater()) 
		{
			animationHandler.sendEmptyMessage(0);
			flag=true;
		}

		try
		{ 
			// delay to alloy for smooth animation
			Thread.sleep(IConstant._delayTime);
		}
		catch (final InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}