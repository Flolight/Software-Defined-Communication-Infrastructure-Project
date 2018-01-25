from mininet.topo import Topo

class TopoSdci (Topo):
	def __init__( self ):
		"Create the SDCI topology..."
		
		#Init
		Topo.__init__(self)
		
		#gi  = self.addDocker('gi',  ip='10.0.0.3', dimage="flolight/gi")
		#gf1 = self.addDocker('gf1', ip='10.0.0.4', dimage="flolight/gf1")
		#gf2 = self.addDocker('gf2', ip='10.0.0.5', dimage="flolight/gf2")
		#gf3 = self.addDocker('gf3', ip='10.0.0.6', dimage="flolight/gf3")

		appserver = self.addDocker('appserver', ip='10.0.0.2', dimage="sdciproject/appserver")

		s1  = self.addSwitch( 's1' )
		
topos = { 'toposdci': ( lambda: TopoSdci() ) }
