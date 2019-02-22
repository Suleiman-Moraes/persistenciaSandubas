//package br.com.senaigo.persistenciasandubas.security.controller;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@CrossOrigin(origins = "*")
//public class AuthenticationRestController {
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwtTokenUtil jwtTokenUtil;
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Autowired
//	private UserService userService;
//
//	@PostMapping(value = "/api/auth")
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest)
//			throws AuthenticationException {
//		final Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
//						authenticationRequest.getPassword()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
//		final String token = jwtTokenUtil.generateToken(userDetails);
//		final User user = userService.findByEmail(authenticationRequest.getEmail());
//		user.setPassword(null);
//		return ResponseEntity.ok(new CurrentUser(token, user));
//	}
//
//	@PostMapping(value = "/api/refresh")
//	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
//		String token = request.getHeader("Authorization");
//		String username = jwtTokenUtil.getUsernameFromToken(token);
//		final User user = userService.findByEmail(username);
//
//		if (jwtTokenUtil.canTokenBeRefreshed(token)) {
//			String refreshedToken = jwtTokenUtil.refreshToken(token);
//			return ResponseEntity.ok(new CurrentUser(refreshedToken, user));
//		} else {
//			return ResponseEntity.badRequest().body(null);
//		}
//	}
//}
