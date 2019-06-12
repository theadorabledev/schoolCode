public class BarnesHutTree{
	private Particle particle = null;
	private Quadrant quadrant;
	private BarnesHutTree NW = null;
	private BarnesHutTree NE = null;
	private BarnesHutTree SE = null;
	private BarnesHutTree SW = null;
	public BarnesHutTree(Quadrant q){
		quadrant = q;
	}
	public boolean external(){
		return(NW == null && NE == null && SE == null && SW == null);
	}
	public void insert(Particle p){
		
		if(particle == null){
			particle = p;
		}else if(external()){
			insertHelper(particle);
			insert(p);
		}else{
			particle = Particle.centerOfMass(particle, p);
			insertHelper(p);
			//insert(p);
		}
	}
	private void insertHelper(Particle p){
		Quadrant nw = quadrant.NW();
		if(nw.contains(p)){
			if(NW == null) NW = new BarnesHutTree(nw);
			NW.insert(p);
			return;
		}
		Quadrant ne = quadrant.NE();
		if(ne.contains(p)){
			if(NE == null) NE = new BarnesHutTree(ne);
			NE.insert(p);
			return;
		}
		Quadrant se = quadrant.SE();
		if(se.contains(p)){
			if(SE == null) SE = new BarnesHutTree(se);
			SE.insert(p);
			return;
		}		
		Quadrant sw = quadrant.SW();
		if(sw.contains(p)){
			if(SW == null) SW = new BarnesHutTree(sw);
			SW.insert(p);
		}
	}
	public void updateForces(Particle p){
		if(external()){
			if(particle != p) p.addForce(particle);
		}else if(quadrant.length()/particle.distanceTo(p) < 2){
			p.addForce(particle);
		}else{
			if( NW != null) NW.updateForces(p);
			if( NE != null) NE.updateForces(p);
			if( SE != null) SE.updateForces(p);
			if( SW != null) SW.updateForces(p);

		}
	}
}