# eSportsLS
Recursive Sorting Algorithms
eSports La Salle is a (fictional) project created by the university to organize the League of Legends game competitions. First, the aim is to organize a league between the best professional teams and then bring together the best players in their respective teams to create a world tournament.
League of Legends is an online MOBA game, where each user controls one of several characters available, each with its own unique abilities properties. Each game consists of two teams of 5 players, each with its position corresponding to the game map (Top, Jungle, Mid, ADC, Support). The winner is the team that manages to destroy the opponent's base.
For the competition, we need to program an algorithm that, based on some data, shows us the best teams. In this way we will be able to obtain performance statistics that allow us to balance the confrontations.
However, for the highlight of the night and of which we are most proud, the world of teams, we have been asked to choose which players deserve to represent their respective countries (since each team is made up of 5 players). That’s why we want them to be places that are repaired on their own merits, feeling as fair as possible (after all, not everyone can play on Faker’s team).

To meet the objectives set out in the introduction, we will have to implement 4 sorting algorithms. These are: Quicksort, Merge Sort, Radix Sort and Bucket Sort.
Each of these algorithms must be able to sort according to the following criteria:
3.1. EQUIPMENT BY winrate:
We will sort the teams by their ranking in the ranking, so the teams must be sorted by their
winrate, decreasing.
3.2. PLAYERS BY NATIONALITY:
In order to have an ordered list of the players we will have for this next event, it is necessary to sort them alphabetically by their nationality, from A to Z. In the event that there is more than one player of the same nationality, will be drawn by name (again from A to Z).
NOTE: This section will not ask you to implement it with the Radix Sort sorting method
3.3. PLAYERS BY COMBINATION OF PRIORITIES:
In order to organize the World Cup, we must choose the best players of each nationality, so we will combine a set of criteria to order the players of each country separately.
First, we will consider the winrate, which will correspond to that of your team. We will also take into account your KDA (Kill, Death and Assistance ratio), having to give more priority first to your winrate and then to the KDA. This will avoid cases in which a player is the one who gets the most kills in a game but leads his team to defeat for not knowing how to get along well in team play.
To calculate the KDA we will use the following formula:
KDA = (kills + assists ∗ 0.5) / (deaths)
