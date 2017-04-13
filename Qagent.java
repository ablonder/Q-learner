import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * 
 * Artificial agent that learns to solve the Grid using Q-learning
 * 
 * @author Aviva Blonder
 *
 */

public class Qagent {

	// a table to hold the learned utility of each action in each state
	HashMap<State, HashMap<String, Double>> Qtable;
	// The grid world that the agent is moving in
	Grid world;
	// A list of discounted cumulative rewards for each of the agent's previous runs
	ArrayList<Double> rewardhist;
	// The discount for past reward
	static final double gamma = .99;
	// importance placed on exploration in softmax
	static final double tau = .01;

	/*
	 * Initializes the agent
	 * 
	 * @param envs - the grid world the agent will be acting in
	 */
	public Qagent(Grid envs) {
		Qtable = new HashMap<State, HashMap<String, Double>>();
		rewardhist = new ArrayList<Double>();
		world = envs;
	}

	/*
	 * The agent travels through the environment until it reaches an absorbing state a given number of times
	 * 
	 * @param trial - number of remaining trials
	 */
	public ArrayList<Double> runiters(int trial, Double epsilon) {
		for(int i = 0; i < trial; i++){
			double reward = 0;
			// re-initialize currentreward and currentS
			ArrayList<Double> currentreward = new ArrayList<Double>();
			State currentS = world.generateStartState();
			while(currentS != world.ABSORBING_STATE){
				// Until it reaches an absorbing state, choose an action and update the Q-table

				// list of possible actions
				String[] posacts = {"up", "down", "left", "right"};
				// HashMap of the possible actions and their Q values
				HashMap<String, Double> acts;
				// chosen action
				String choiceact = "";
				// random number generator
				Random random = new Random();

				// check to see if the current state is in the Q-table, if not add it
				if(!Qtable.containsKey(currentS)){
					acts = new HashMap<String, Double>();
					for(String a:posacts){
						acts.put(a, 0.0);
					}
					Qtable.put(currentS, acts);
				} else {
					// Otherwise, grab the HashMap of possible actions
					acts = Qtable.get(currentS);
				}

				// Choose an action
				// first find the sum of the Q-values of each action and the denominator of the softmax
				double totalq = 0;
				double softtotal = 0;
				for(Double a:acts.values()){
					totalq += a;
					softtotal += Math.exp(a/tau);
				}
				// if that's zero, choose an action at random
				if(totalq == 0){
					choiceact = posacts[random.nextInt(4)];
				} else if(epsilon == null) {
					// Otherwise, if no value of epsilon was given, use softmax
					// generate a uniform random number
					double draw = random.nextDouble();
					// calculate the cumulative probability of each choice and choose the one that includes the draw
					double prob = 0;
					for(String a:acts.keySet()){
						prob += Math.exp(acts.get(a)/tau)/softtotal;
						if(prob >= draw){
							choiceact = a;
							break;
						}
					}
				} else {
					// if a value of epsilon was provided, use epsilon greedy
					// draw a random number
					double draw = random.nextDouble();
					// if it's less than epsilon, choose a random action
					if(draw < epsilon){
						choiceact = posacts[random.nextInt(4)];
					} else {
						// otherwise choose the action with the highest Q-value
						double maxreward = acts.get(posacts[0]);
						choiceact = posacts[0];
						for(int k = 1; k < 4; k++){
							double nextreward = acts.get(posacts[k]);
							if(nextreward > maxreward){
								maxreward = nextreward;
								choiceact = posacts[k];
							}
						}
					}
				}
				// update the Q-table based on the choice
				// find the reward of this action and the next state
				reward = world.generateReward(currentS, choiceact);
				currentS = world.generateNextState(currentS, choiceact);
				// append this reward to current reward
				currentreward.add(reward);
				// find the maximum reward off all the actions from the next state
				double maxreward = 0;
				// if the next state has been seen already, grab the q values of possible actions in the next state
				if(Qtable.containsKey(currentS)){
					HashMap<String, Double> newacts = Qtable.get(currentS);
					// find the actual max reward
					maxreward = newacts.get(posacts[0]);
					for(int k = 1; k < 4; k++){
						double nextreward = newacts.get(posacts[k]);
						if(nextreward > maxreward){
							maxreward = nextreward;
						}
					}
				}
				// calculate the learning rate parameter
				double alpha = .5; // 1/(double) (currentreward.size()+1);
				// calculate the new Q-value and add it to the Q-table
				acts.put(choiceact, ((1-alpha)*acts.get(choiceact) + alpha*(reward+gamma*maxreward)));
			}
			reward = 0.0;
			for(int j = 0; j < currentreward.size(); j++){
				reward += currentreward.get(j)*Math.pow(gamma, j);
			}
			rewardhist.add(reward);
		}
		return rewardhist;
	}
	
	public ArrayList<Double> runiters(int trial){
		return runiters(trial, null);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Auto-generated method stub
		Grid g = new Grid();
		Qagent agent1 = new Qagent(g);
		ArrayList<Double> softmax = agent1.runiters(100);
		System.out.println(softmax);
		Qagent agent2 = new Qagent(g);
		ArrayList<Double> egreedy1 = agent2.runiters(100, .1);
		System.out.println(egreedy1);
		Qagent agent3 = new Qagent(g);
		ArrayList<Double> egreedy5 = agent3.runiters(100, .5);
		System.out.println(egreedy5);
	}

}
