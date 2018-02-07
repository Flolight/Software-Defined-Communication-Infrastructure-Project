from mininet.topo import Topo

class TopoSdci (Topo):
	def __init__( self ):
		"Create the SDCI topology..."
		
		#Init
		Topo.__init__(self)
		
		gi  = self.addDocker('gi',  ip='10.0.0.3', dimage="sdciproject/gi")
		gf1 = self.addDocker('gf1', ip='10.0.0.4', dimage="sdciproject/gf1")
		gf2 = self.addDocker('gf2', ip='10.0.0.5', dimage="sdciproject/gf2")
		gf3 = self.addDocker('gf3', ip='10.0.0.6', dimage="sdciproject/gf3")
		appserver = self.addDocker('appserver', ip='10.0.0.2', dimage="sdciproject/appserver")
		#gi  = self.addDocker('gi',  ip='10.0.0.3', dimage="ubuntu:trusty")
		#gf1 = self.addDocker('gf1', ip='10.0.0.4', dimage="ubuntu:trusty")
		
		#appserver = self.addDocker('appserver', ip='10.0.0.2', dimage="flolight/appserver")

		#gi  = self.addDocker('gi',  ip='10.0.0.3', dimage="flolight/gi")
		#gf1 = self.addDocker('gf1', ip='10.0.0.4', dimage="flolight/gf1")
		#gf2 = self.addDocker('gf2', ip='10.0.0.5', dimage="flolight/gf2")
		#gf3 = self.addDocker('gf3', ip='10.0.0.6', dimage="flolight/gf3")
		dc  = self.addHost('dc',  ip='10.0.0.8', dimage="docker:dind")
		#dc  = self.addDocker('dc',  ip='10.0.0.8', dimage="docker:dind")
		#gw  = self.addDocker('gw',  ip='10.0.0.9', dimage="flolight/vnfgw")
		
		s1  = self.addSwitch( 's1' )
		s2  = self.addSwitch( 's2' )

		self.addLink(gf1, s1)
		self.addLink(gf2, s1)
		self.addLink(gf3, s1)
		self.addLink(gi, s1)
		self.addLink(appserver, s1)
		self.addLink(s1, s2)
		self.addLink(dc, s2)

		
topos = { 'toposdci': ( lambda: TopoSdci() ) }
