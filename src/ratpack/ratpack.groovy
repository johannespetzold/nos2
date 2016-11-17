import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.User
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.RepositoryService
import org.eclipse.egit.github.core.service.UserService

import static ratpack.groovy.Groovy.ratpack
import ratpack.exec.Blocking

ratpack {
    handlers {
        get {
            def client = new GitHubClient()
            client.setOAuth2Token("482fd0d2edff1f60b7ec3a32b110b2f33d2148ff")
            def repoService = new RepositoryService(client)
            Blocking.get() {
                repoService.createRepository(new Repository(
                        owner: new User(name: 'johannespetzold'),
                        name:  'nos5testing'
                ))
            } then {
                render 'repo created'
            }
        }
        get(":name") {
            render "Hello $pathTokens.name!"
        }
    }
}