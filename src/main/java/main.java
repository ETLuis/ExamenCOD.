import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

public class main {

    public static void main(final String[] args) {
        //Creo un token inicializado
        final String token = args[0];
        //Hago un metodo "create" para meter el token y lo guardo en client
        final DiscordClient client = DiscordClient.create(token);
        //Hago un metodo "login" para la sesión y lo guardo en gateway
        final GatewayDiscordClient gateway = client.login().block();
        //Creo un mensaje de evento
        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            //Le doy un mensaje y lo guardo en "message"
            final Message message = event.getMessage();
            //Si el mensaje es igual a "!ping" ocurre lo de dentro del if
            if ("!ping".equals(message.getContent())) {
                //Le doy un canal y lo guardo en "channel"
                final MessageChannel channel = message.getChannel().block();
                //Creo un mensaje "!Pong"
                channel.createMessage("Pong!").block();
            }
        });
        //Me desconecto de la sesión
        gateway.onDisconnect().block();
    }
}

