# Desafio

Realizar um exercicio simples, de maneira a comprovar conhecimentos basicos nas tecnologias utilizadas na Empresa.

## Tecnologias Utilizadas

- JAVA, Spring Boot, Restful, Json

## Prerequisitos

E necessario as ferramentas maven, git e tomcat( startado pelo spring boot ) estejam instalado na maquina.

## Fontes do Projeto
Esta no diretorio desafio-back-end ( aplicacao gerada no modelo de IDE Eclipse )

Executar por dentro do Eclipse ( IDE )
-------------------------------------------------------------------
	1) Instalar o Eclipse
	
	2) Baixar o fonte da aplicacao :
	   git clone https://github.com/avmartins/desafio-back-end.git
	   
	3) Importar e abrir o projeto no eclipse   
	   
Compilar a aplicacao
-------------------------------------------------------------------

	1) Pelo Eclipse
	
		- Executar mvn install
	   
	2) Entrar no diretorio onde baixou o projeto e executar 
	   mvn clean install  	
	   
Executar a aplicacao
-------------------------------------------------------------------

1) Pelo Eclipse
	
	1) Executar o teste via container

		1) Para subir o container 
			Run  br.com.anderson.springboot.DesafioServicesApplication
	
		2) Executar a url para autenticar 
			http://localhost:8080/ 
	
			User : admin1 Senha : secret1 - autentica com admin
			User : user1  Senha : secret1 - mostra pagina erro
	
		3) Executar a url para executar o webservice 
			http://localhost:8080/buscaFeed
			
	2) Executar o teste via Run	
		Run br.com.anderson.springboot.Main	

2) Pela Linha de comando

	1) Ir para o diretorio do projeto e executar o comando abaiixo para subir o container
		mvn spring-boot:run
		
	2) Executar a url para autenticar 
			http://localhost:8080/ 
	
			User : admin1 Senha : secret1 - autentica com admin
			User : user1  Senha : secret1 - mostra pagina erro
	
		3) Executar a url para executar o webservice 
			http://localhost:8080/buscaFeed
		
Duas formas de ver o resultado do arquivo json gerado
-------------------------------------------------------------------
	
	1) No Browser
	2) Na execução cria um arquivo chamado feed.json



# Desafio Proposto

Criar um crawler que leia [este feed] (http://revistaautoesporte.globo.com/rss/ultimas/feed.xml)
e retorne um json estruturado da seguinte forma:

```
{
    'feed': [
        'item': {
            'title': 'titulo da materia',
            'link': 'url da materia',
            'description': [
                {
                    'type': 'text',
                    'content': 'conteudo da tag'
                },
                {
                    'type': 'image',
                    'content': 'url da imagem'
                },
                {
                    'type': 'links',
                    'content': ['urls dos links', ...]
                }
            ]
        },
        'item': {
            ...
        },
        'item': {
            ...
        },
        'item': {
            ...
        }
    ]
}
```

## Requisitos

* O campo titulo deve ser identico ao item do feed;
* O campo links deve ser identico ao item do feed;
* O campo description deve ser estruturado da seguinte forma: 
  * tag p (com conteudo) deve gerar um bloco do tipo 'text' com o texto como content;
  * tag div//img deve gerar um bloco do tipo 'image' com o endereco da imagem como content;
  * tag div//ul deve gerar um block do tipo 'links', com o endereco de cada link no content;


Para escrever o teste sinta-se a vontade para usar linguagem e tecnologia que desejar mas lembre-se a proposta e avaliar a sua capacidade como desenvolvedor.


## Diferenciais

* Cobertura de testes;
* Projeto conteinerizado;
* Organizacao e legibilidade do codigo;
* Desafio extra:
  * Implementar como um webservice;
  * Permitir que somente usuários cadastrados possam acessar o webservice;

## Autor

* **Anderson Virginio Martins** - (https://github.com/avmartins/desafio.git)

