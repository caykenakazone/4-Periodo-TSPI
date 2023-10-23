🍃

**Trabalho 01 - MongoDB**

(BD Orientado a Documentos)

**Disciplina: Banco de Dados NoSQL**

**Prof.**: Camilo Barreto

**Período:** 4

**Curso:** Tec. Sistemas para Internet

**IFTM - Campus Uberlândia Centro**

**Informações:**

\- **Data de publicação:** 06/10/2023

\- **Data de entrega**: 20/10/2023

\- **Local de entrega:** Class Room

\- **Formato de entrega:** github (detalhes no final do documento).

\- **Pontos:** 15 pts


Passo 1 -

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.001.png)

O arquivo JSON se encontra disponível dentro do repositório do GitHub.

A escolha de cada campo foi feita de acordo com o Modelo do BD que foi passado. Irei repassar as 	informações mais importantes sobre os campos escolhidos.

tasks: Uma lista de tarefas relacionadas a este projeto. Cada tarefa é representada como um documento embutido contendo as informações relacionadas à tarefa.

title (VARCHAR): O título da tarefa.

description (VARCHAR): Uma descrição mais detalhada da tarefa. start\_date (DATE): A data de início da tarefa. Indica quando a tarefa foi iniciada ou está programada para começar.

deadline\_date (DATE): A data limite para a conclusão da tarefa. É a data em que a tarefa deve ser concluída.

priority (INTEGER): Uma classificação feita com números que representa a prioridade da tarefa. 

status (VARCHAR): Indica o estado atual da tarefa, como "Pendente", "Em Progresso" ou "Concluída".

Passo 2 -

Arquivo JSON no GitHub.

Passo 3 -

O Schema de criação é o mesmo para o Passo 2, logo não coloquei ele aqui, irei colocar apenas os outros scripts usados

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.002.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.003.png)

Passo 4 -

Inserção feita pelo Json disponível no GitHub
![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.004.png)

Crie 15 queries para busca de projetos, tarefas e usuários;

1 - Todas as tarefas de um projeto que estejam com status “To-Do” (a fazer)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.005.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.006.png)

2 - Todas as tarefas de um usuário que estejam com o status “To-Do” (a fazer)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.007.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.008.png)

3 - Todas as tarefas com o prazo de entrega vencido.

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.009.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.010.png)

4 - Todas as tarefas ordenadas por prioridade:

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.011.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.012.png)

5 - Todas as tarefas concluídas em um projeto específico:

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.013.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.014.png)

6 - Mostrar todas as tasks dos projetos
![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.015.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.016.png)

7 - Todas as tarefas atribuídas a usuários ordenadas por prioridade

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.017.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.018.png)

8 -Todas as tarefas que foram concluídas em um determinado período de tempo (por exemplo, de "2023-10-15" a "2023-10-30")

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.019.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.020.png)

9 - Contar as tarefas que estão em progresso (status "Em Progresso")

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.021.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.022.png)

10 - Contar Tasks concluidas por todos usuários

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.023.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.024.png)

11 - Tasks concluidas nos ultimos 7 dias

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.025.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.026.png)

12 - Tasks com prazo de entrega nos proximos 3 dias

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.027.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.028.png)

13 - Todas as tarefas concluídas atribuídas a usuários com prioridade 1

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.029.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.030.png)

14 - Todos os projetos ordenados pelo número de tarefas em progresso (status "Em Progresso")

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.031.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.032.png)

15 - Todos os usuários que têm pelo menos uma tarefa concluída com prioridade 1

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.033.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.034.png)

Passo 5 -

1 - Alterar quem será o usuário responsável pela tarefa;

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.035.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.036.png)

2 - Dilatar o prazo de entrega da tarefa;

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.037.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.038.png)

3 - Mudar o status de “In Progress” para “Complete”;

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.039.png)

Antes:

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.040.png)

Depois:

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.041.png)

4 - Alterar a prioridade de uma tarefa específica

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.042.png)

Antes:

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.043.png)
Depois:

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.044.png)

5 - Mudar o status de "Pendente" para "Em Progresso" para uma tarefa específica

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.045.png)

Antes: 

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.046.png)

Depois:

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.047.png)

Passo 6 -

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.048.png)

![](Aspose.Words.4f22a8b4-4e99-4848-bb95-029302dc86ee.049.png)

Os log’s foram enviados pelo github no formato MarkDown
