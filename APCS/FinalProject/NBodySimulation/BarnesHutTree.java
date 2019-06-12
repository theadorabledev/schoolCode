public class BarnesHutTree{
	private Body body = null;
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
	public void insert(Body b){
		
		if(body == null){
			body = b;
		}else if(external()){
			insertHelper(body);
			insert(b);
		}else{
			body = Body.centerOfMass(body, b);
			insertHelper(b);
			//insert(p);
		}
	}
	private void insertHelper(Body b){
		Quadrant nw = quadrant.NW();
		if(nw.contains(b)){
			if(NW == null) NW = new BarnesHutTree(nw);
			NW.insert(b);
			return;
		}
		Quadrant ne = quadrant.NE();
		if(ne.contains(b)){
			if(NE == null) NE = new BarnesHutTree(ne);
			NE.insert(b);
			return;
		}
		Quadrant se = quadrant.SE();
		if(se.contains(b)){
			if(SE == null) SE = new BarnesHutTree(se);
			SE.insert(b);
			return;
		}		
		Quadrant sw = quadrant.SW();
		if(sw.contains(b)){
			if(SW == null) SW = new BarnesHutTree(sw);
			SW.insert(b);
		}
	}
	public void updateForces(Body b){
		if(external()){
			if(body != b) b.addForce(body);
		}else if(quadrant.length()/body.distanceTo(b) < 2){
			b.addForce(body);
		}else{
			if( NW != null) NW.updateForces(b);
			if( NE != null) NE.updateForces(b);
			if( SE != null) SE.updateForces(b);
			if( SW != null) SW.updateForces(b);

		}
	}
}
