import java.util.*;
public class VoterBallots{

    private Map<String,Integer> voteCount; 
    // key is the candidate name, value is the
    // number of votes received by that candidate.

    // precondition: each entry in ballotList is a Set representing
    //               one voter's ballot.
    // postcondition: voteCount.get(candidate) is the total number of
    //                times candidate appears on ballots in ballotList.
    public VoterBallots(List<Set<String>> ballotList){
	voteCount = new HashMap<String, Integer>();
	for(Set<String> ballot : ballotList){
	    for(String candidate : ballot){
		if(!voteCount.containsKey(candidate)) voteCount.put(candidate, 0);
		voteCount.put(candidate, voteCount.get(candidate) + 1);
	    }
	}
    }
    // postcondition: returns an Integer object with value equal
    //                to the key in the Map voteCount.
	   
    private Integer maxVotes(){
	int max = 0;
	for(String candidate : voteCount.keySet()){
	    if(voteCount.get(candidate) > max) max = voteCount.get(candidate);
	}
	return max;
    }


    // postcondition: returns the set containing the candidate(s)
    //                with the most votes
    public Set<String> candidatesWithMost(){
	//O(n) or O(C) where C is the number of candidates
	HashSet<String> candidates = new HashSet<String>();
	int max = maxVotes();
	for(String candidate : voteCount.keySet()){
	    if(voteCount.get(candidate) == max) candidates.add(candidate);
	}
	return candidates;
    }

    // other methods not shown
}
