package ru.dataart.javaschoolexam.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.dataart.javaschoolexam.entities.Article;
import ru.dataart.javaschoolexam.entities.Section;
import ru.dataart.javaschoolexam.services.ArticlesService;
import ru.dataart.javaschoolexam.services.SectionsService;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InitiateUtils implements CommandLineRunner {
    private final SectionsService sectionsService;
    private final ArticlesService articlesService;

    @Override
    public void run(String... args) throws Exception {
        long countOfSessions = sectionsService.getCountOfRows();
        long countOfArticles = articlesService.getCountOfRows();

        if (countOfSessions == 0 && countOfArticles == 0) {
            Section sportSection = new Section("Спорт", new ArrayList<>());
            Section financesSection = new Section("Финансы", new ArrayList<>());

            sectionsService.saveSections(List.of(sportSection, financesSection));

            Article sportArticle1 = new Article();
            sportArticle1.setTitle("Россия выиграла в чемпионате мира по футболу!");
            sportArticle1.setText("Ха, поверили? Значит у вас есть надежда...");
            sportArticle1.setDateOfCreation(Timestamp.from(Instant.now()));
            sportArticle1.setSection(sportSection);

            Article sportArticle2 = new Article();
            sportArticle2.setTitle("Россия получила золотые медали в олимпиаде в Токио!");
            sportArticle2.setText("Наши спортсмены были невероятны и одержали победу в...");
            sportArticle2.setDateOfCreation(Timestamp.from(Instant.now()));
            sportArticle2.setSection(sportSection);


            Article financeArticle1 = new Article();
            financeArticle1.setTitle("Стабильность - признак мастерства!");
            financeArticle1.setText("Доллар вновь вырос на 2 рубля...");
            financeArticle1.setDateOfCreation(Timestamp.from(Instant.now()));
            financeArticle1.setSection(financesSection);
            articlesService.saveArticle(financeArticle1);

            articlesService.saveArticles(List.of(sportArticle1, sportArticle2));

            for (int i = 0; i < 30; i++) {
                Article article = new Article();
                article.setText("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci aliquam architecto at beatae consequuntur dolorum est fuga incidunt iusto laborum libero molestiae, natus non quod reiciendis similique temporibus. Alias atque earum ipsam numquam quod, reiciendis voluptates. Inventore minus perspiciatis provident quo sed similique sit tempora veritatis voluptatibus? At commodi cupiditate dicta laudantium nihil nostrum veritatis. Assumenda ducimus harum quas? Animi autem cupiditate enim, fugiat itaque labore saepe. Asperiores dolor hic ipsa laboriosam mollitia possimus reiciendis repellat repellendus voluptas voluptatibus. Alias aut blanditiis ea, enim eveniet explicabo hic, laudantium neque odit porro quasi qui, rem repudiandae. Ab adipisci animi, asperiores beatae, culpa dicta eos esse eum ex fugiat fugit, impedit incidunt ipsa maiores minus mollitia natus neque numquam odit optio perspiciatis qui quisquam recusandae saepe tenetur veniam voluptas. Aliquam aspernatur, assumenda at blanditiis consequatur cum cumque debitis, explicabo facere fugiat hic illum incidunt, iste magnam minus molestiae molestias natus optio placeat repellat repellendus saepe sapiente sit suscipit temporibus ut vero voluptas. Autem consequuntur delectus illum, quis recusandae sunt. Doloribus est modi molestiae numquam placeat, quasi suscipit totam voluptas. Dolores ducimus facere fugiat quas similique. A aliquam atque corporis, doloribus ducimus eaque excepturi explicabo fugiat iure nisi nostrum, nulla numquam perspiciatis placeat provident quisquam ratione rem repellendus tempore tenetur vel veniam voluptatem. Aliquam, atque officia provident totam veritatis voluptates. Beatae commodi dicta earum enim et id laborum minima minus natus, nesciunt, quaerat quasi quod reprehenderit sequi similique tempora voluptas voluptatem. Corporis fugit labore molestiae nisi quas? Blanditiis commodi illum natus nemo? Cum eaque fugiat illum ipsa minima totam unde veritatis. Aut cumque ex minima modi optio placeat quidem! Asperiores autem exercitationem maiores, minima molestias nam nemo neque porro quibusdam voluptatibus. Alias hic incidunt minus, natus nihil perferendis quisquam quos repudiandae sed? Cupiditate dicta dolorem explicabo fugiat laudantium nostrum, officia praesentium quaerat quia soluta! Ab alias aliquam animi autem consectetur delectus dignissimos dolore doloribus eius error est facere fugiat iste laudantium magni maxime mollitia nam necessitatibus nisi non nulla odit, officia officiis placeat porro possimus provident qui quo quod reiciendis rerum sequi tempora tenetur totam unde voluptate voluptates. Ad alias aperiam, atque autem consequuntur eos esse iusto, libero maiores maxime nesciunt non porro possimus reiciendis suscipit ut vel voluptatum. Accusantium blanditiis ducimus nam voluptas? Aliquid commodi magni modi molestias ullam! Modi mollitia necessitatibus obcaecati optio. Accusamus adipisci blanditiis, delectus distinctio, doloremque eveniet ex hic, libero nam nisi non quod sapiente sit ullam veniam. A, aperiam at atque beatae blanditiis consectetur, consequatur consequuntur deleniti deserunt dolor doloremque error eveniet explicabo impedit in incidunt ipsa, ipsum iste laboriosam minima nihil non omnis perspiciatis quae quisquam rerum sapiente sequi sit vel voluptatem! A adipisci aut corporis cum debitis dignissimos ea earum enim error eveniet, expedita fuga fugit id incidunt ipsa iusto magnam magni modi necessitatibus pariatur quas quia quibusdam rem, repellendus saepe sequi similique? Alias asperiores cum eaque error esse eum fuga, harum impedit itaque maiores minima modi nisi odit perferendis placeat porro, praesentium provident repellendus saepe soluta sunt tempora ullam ut vel velit vitae voluptas. Aperiam laborum molestiae recusandae saepe. Aliquid atque blanditiis, commodi consequuntur dolorem doloribus dolorum ducimus earum fuga fugiat fugit, impedit, ipsam laboriosam libero minima minus modi mollitia nam obcaecati perferendis qui quia quos recusandae sapiente sint sunt tempora unde. Assumenda culpa esse expedita explicabo facilis fuga ipsa, nobis. Aut commodi distinctio eaque eius fugit illum, ipsa itaque laudantium natus, nemo numquam quae quas quos temporibus, tenetur. A ab ad assumenda corporis deserunt doloremque ducimus eaque eius esse eum excepturi explicabo in ipsum iste itaque, laboriosam magni mollitia nesciunt officia officiis pariatur praesentium quaerat quia quod ratione reiciendis rem repudiandae sapiente sint totam ullam vel vero voluptas? Adipisci quaerat, quia. Aliquam aliquid, aut, consequuntur debitis delectus dicta dignissimos doloremque ea eaque, explicabo fuga harum illum incidunt ipsa labore magni molestias nesciunt nihil optio placeat possimus quas qui quidem quo repellat repellendus sequi tempora temporibus tenetur vel? A amet cumque debitis, deleniti eaque esse excepturi ipsam, maxime molestiae officiis praesentium quas recusandae sint! Ab ad adipisci, alias aperiam assumenda cumque cupiditate doloremque dolorum ducimus ea earum eos excepturi facilis illum in inventore itaque mollitia nam nemo neque nihil nobis nostrum optio quasi quidem quis quo quos rem sunt, temporibus totam veniam veritatis vitae voluptas voluptate voluptatem voluptatibus. At deserunt neque nulla sit. Alias, cum dignissimos eligendi, fuga hic iusto laborum nobis obcaecati omnis pariatur porro, quia quisquam sapiente unde veniam voluptate voluptatibus. Animi aperiam est ex maxime nam numquam officiis pariatur porro quae, quaerat quibusdam quidem, quisquam ut vel, veritatis? Ab at, est expedita nemo quod quos velit? Blanditiis commodi hic, illo iure laboriosam necessitatibus nemo odio officia quisquam repellendus. Ab accusantium adipisci aliquam amet assumenda beatae blanditiis consectetur deleniti error excepturi exercitationem explicabo hic illum iste, itaque iure magnam necessitatibus nesciunt nihil porro quaerat qui quibusdam quidem quis reiciendis rem repellat rerum saepe sapiente soluta sunt tenetur totam unde ut vel, vitae voluptas? Ad beatae cumque dolorem, eum in laboriosam numquam quaerat quas qui quibusdam quisquam quo quod sed tempora voluptates! Accusamus esse natus qui reprehenderit. Ab ducimus excepturi ipsam laudantium possimus quam. Amet atque aut culpa dolores ducimus eos eveniet fugiat ipsum mollitia nam perspiciatis, quae qui rem repellendus repudiandae unde voluptate? Ab asperiores commodi dolores doloribus ducimus enim esse, eum excepturi fugiat hic ipsum maxime minima mollitia nemo odio optio perferendis provident quia quo sint suscipit tempora tenetur vitae? Architecto atque blanditiis commodi cum dignissimos, dolor dolore enim fuga ipsa iste itaque laboriosam libero nemo neque nesciunt possimus provident similique sunt unde voluptatibus? Aperiam beatae delectus dolorem dolores error iure molestiae natus nisi omnis vero. Accusantium alias amet aspernatur atque consequuntur cum earum eius esse et impedit ipsa laborum nesciunt officia quaerat qui quibusdam reiciendis reprehenderit sapiente veniam, voluptatibus! Eveniet provident quasi repudiandae sint, tempora vero! Asperiores aspernatur at aut, autem beatae, commodi delectus ea et explicabo fuga id illum minima modi mollitia neque non nulla officiis omnis pariatur quae quis recusandae rem repellendus repudiandae sed tempore, voluptatibus. Consequatur dicta dolores, in ipsam molestias placeat provident quaerat quia. Assumenda, quam! ");
                article.setDateOfCreation(Timestamp.valueOf(LocalDateTime.now()));
                if (i < 10) {
                    article.setTitle("Новость " + i + financesSection.getName() + "!");
                    article.setSection(financesSection);
                } else if (i < 20){
                    article.setTitle("Новость " + i + sportSection.getName() + "!");
                    article.setSection(sportSection);
                } else {
                    article.setTitle("Обычная новость " + i  + "!");
                }
                articlesService.saveArticle(article);
            }
        }
    }
}
