package Game;

import Cards.*;
import Cards.ColoredCards.ActionCards.Action;
import Cards.WildCards.Wild;
import Player.PlayerQueue;

public class GameCommand {
    private final DrawPile drawPile;
    private final DiscardPile discardPile;
    private final PlayerQueue players;

    public GameCommand(DrawPile drawPile, DiscardPile discardPile, PlayerQueue players) {
        this.drawPile = drawPile;
        this.discardPile = discardPile;
        this.players = players;
    }

    public DrawPile getDrawPile() {
        return drawPile;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public PlayerQueue getPlayerQueue(){
        return players;
    }

    public void updateGameStatus(DrawPile drawPile, DiscardPile discardPile, PlayerQueue players){
        GameCommand gameContext = new GameCommand(drawPile, discardPile, players);
    }

    public void getPlayerTurn(){
        System.out.println("It's " + players.getCurrentPlayer().getName() + " turn");
    }

    public boolean isPlayable(){
        for(Card card : players.getCurrentPlayer().getCardsInHand()){
            if(card.isValid(discardPile.getTop())){
                return true;
            }
        }
        return false;
    }

    public boolean isPlayableCard(int pos){
        return players.getCurrentPlayer().getChosenCard(pos).isValid(discardPile.getTop());
    }

    public Card playCard(int pos){
        Card card = players.getCurrentPlayer().playCard(pos);
        System.out.println(players.getCurrentPlayer().getName() + " played: " + card);
        if(card instanceof Action){  //if it was a action card
            Action actionCard = (Action) card;
            actionCard.performAction(this);
        }else if(card instanceof Wild){ //if it was a wild card
            Wild wildCard = (Wild) card;
            wildCard.performAction(this);
        }else{
            players.getNextPlayer(); //move to the next player if no special cards existed (no actions)
        }
        return card;
    }
}