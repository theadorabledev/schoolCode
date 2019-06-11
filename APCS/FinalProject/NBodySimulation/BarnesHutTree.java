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
	public void addParticle(Particle p){
		
		if(particle == null){
			particle = p;
		}else if(external()){
			insertHelper(particle);
			addParticle(p);
		}else{
			particle = Particle.centerOfMass(particle, p);
			insertHelper(p);
		}
	}
	private void insertHelper(Particle p){
		Quadrant nw = quadrant.NW();
		Quadrant ne = quadrant.NE();
		Quadrant se = quadrant.SE();
		Quadrant sw = quadrant.SW();
		if(nw.contains(p)){
			if(NW == null) NW = new BarnesHutTree(nw);
			NW.addParticle(p);
		}else if(ne.contains(p)){
			if(NE == null) NE = new BarnesHutTree(ne);
			NE.addParticle(p);
		}else if(se.contains(p)){
			if(SE == null) NE = new BarnesHutTree(se);
			SE.addParticle(p);
		}else{
			if(SW == null) NE = new BarnesHutTree(sw);
			SW.addParticle(p);
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