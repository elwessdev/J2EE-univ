@RestController
@RequestMapping("/api/users")
public class UserController {

    @Value("${eureka.instance.instance-id:${random.uuid}}")
    private String instanceId;

    // Other methods and dependencies...

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok()
                .header("X-Instance-Id", instanceId)
                .body(users);
    }
}