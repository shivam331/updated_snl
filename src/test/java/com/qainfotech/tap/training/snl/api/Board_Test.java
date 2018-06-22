package com.qainfotech.tap.training.snl.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

//import com.qainfotech.tap.training.snl.api.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Board_Test {
	
	@Test(expectedExceptions = {PlayerExistsException.class})
	public void registerPlayer_Uniqueness_Test() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		Board register = new Board();
		
		  register.registerPlayer("shivam");
		register.registerPlayer("shivam");
		
		}
	@Test(expectedExceptions = {MaxPlayersReachedExeption.class})
	public void registerplayer_length() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		Board register = new Board();
		register.registerPlayer("shivam");
		register.registerPlayer("shivam1");
		register.registerPlayer("shivam2");
		register.registerPlayer("shivam3");
		register.registerPlayer("shivam4");
		
	}
	@Test 
	public void register_player_valid_data() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		Board register = new Board();
	String get_name = 	(String) register.registerPlayer("shivam").getJSONObject(0).get("name");
		Assert.assertEquals(get_name,"shivam");


	}
	
	
	@Test(expectedExceptions = {GameInProgressException.class})
	public void registerplayer_position_test() throws FileNotFoundException, UnsupportedEncodingException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption {
		Board register = new Board();
		register.registerPlayer("shivam");
		register.registerPlayer("shivam1");
		
		JSONObject data = register.getData();
		JSONArray data_arr = data.getJSONArray("players");
		JSONObject single_player = data_arr.getJSONObject(0);
		single_player.put("position", 5);
		 register.registerPlayer("shivam2");
		
		
	}
		
	

		
	@Test
	public void deleteusertest() throws FileNotFoundException, UnsupportedEncodingException, IOException, JSONException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, NoUserWithSuchUUIDException {
		Board register = new Board();
		//UUID uid = UUID.fromString((String) register.registerPlayer("shivam").getJSONObject(0).get("uuid"));
		UUID uid = (UUID)register.registerPlayer("shivam").getJSONObject(0).get("uuid");

		//JSONArray a = register.deletePlayer(uid);
		System.out.println(register.deletePlayer(uid));
			
	}

	@Test(expectedExceptions = {InvalidTurnException.class})
	public void roll_dice_invalid_turn_user() throws FileNotFoundException, UnsupportedEncodingException, IOException, JSONException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException 
	{	
	Board register = new Board();
		
		UUID uid = UUID.fromString((String) register.registerPlayer("shivam").getJSONObject(0).get("uuid"));
		UUID uid1 = UUID.fromString((String) register.registerPlayer("shivam1").getJSONObject(1).get("uuid"));
		
		
		register.rollDice(uid1);
	}
	@Test
	public void roll_dice_valid_turn_user() throws FileNotFoundException, UnsupportedEncodingException, IOException, JSONException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, InvalidTurnException {
		Board register = new Board();
		UUID uid = UUID.fromString((String) register.registerPlayer("shivam").getJSONObject(0).get("uuid"));
		
		UUID uid1 = UUID.fromString((String) register.registerPlayer("shivam1").getJSONObject(1).get("uuid"));
		//System.out.println();
		UUID returned = (UUID) register.rollDice(uid).get("playerUuid") ;
		
			Assert.assertEquals(uid, returned);
		
		
	}
		
	

}
