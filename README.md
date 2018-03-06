# CessnaSkyhawk

**Fiachra Dunn, Lucy Byrne, Michael Jordan**
---
**General Strategy:**
We buy everything we land on if we can afford it; sites, stations and utilities.	

**Building:**
We aim to build at least 1 house across a colour group. Then 2, then 3 etc and then a hotel. We are always building throughout the game wherever possible.

**Avoiding Bankruptcy:**
If our balance goes below 0, we first mortgage our utilities, then our stations.
We then mortgage the least desirable colour groups first.
We first checked if the other player already owned another property on that colour group.
If they did, we mortgaged a property from that colour group straight away because the chances of getting a monopoly on that group were very low.
Next, we check if we are very far away from a monopoly, ie we only own 1 property of the colour group. 
Then, if our balance was still below 0, we mortgaged those where we only owned 2. As a last resort, we then began mortgaging properties where we have a monopoly.

If mortgaging still left our balance below 0 we move to the demolish function. 
We demolish 1 house at a time starting with the least desirable.
Then we demolish the properties with only 1 house, then with only 2, etc.
If our balance is still below 0, it goes back to the mortgage function and tries to get more money that way. 

If all of these attempts fail, we declare bankruptcy.
We only ever mortgage or demolish 1 thing at a time to keep as much of our assets as possible.

**When in Jail:**	
When we're in Jail, we first check if the other player has monopoly. 

If they do: 
We stay in jail and collect rent instead of risking landing on their squares and paying a large rent. So we roll.

If they don't have a monopoly yet:
We first check if we have a card.
If we do, we then check our balance. If our balance is large, we pay out to save our card for a situation we're in jail with low funds.
If we have a low balance we use the card.
If we don't have the card, we pay out if our balance allows it. 
If our balance is too low, we roll.

**The getDecision() function:**
We always return "pay" for this.
Due to our strategy being buying and building as much as possible, the risk of pulling a chance card instead of paying 50 was never worth it.
Chance cards have a card that makes you pay £40 per house which would be much worse to get than paying the fine.
	
*[We used a Monopoly strategy article to order the colour groups by least desirable to most desirable](http://www.retroactive-vintage-games.com/games-articles/monopoly-best-property-groups.asp)
