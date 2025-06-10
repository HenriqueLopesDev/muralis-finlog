import { IMapper } from '@/common/types/IMapper'
import { CreateExpenseRequest } from '@/modules/expenses/types/Expense'
import { removeNonNumericCharacters } from '@/common/utils/formatters'
import { CreateExpenseFormData } from '@/modules/expenses/validation/createExpenseValidationSchema'

export class CreateExpenseToCreateExpenseRequest
  implements IMapper<CreateExpenseFormData, CreateExpenseRequest>
{
  map(createExpenseFormData: CreateExpenseFormData): CreateExpenseRequest {
    return {
      description: createExpenseFormData.description,
      value:
        Number(removeNonNumericCharacters(createExpenseFormData.value)) / 100,
      date: new Date(createExpenseFormData.expenseDate).toISOString(),
      paymentTypeId: Number(createExpenseFormData.paymentType),
      category: {
        name: createExpenseFormData.category,
        description: createExpenseFormData.categoryDescription,
      },
      address: {
        zipCode: removeNonNumericCharacters(createExpenseFormData.cep),
        street: createExpenseFormData.street,
        streetNumber: createExpenseFormData.streetNumber,
        neighborhood: createExpenseFormData.neighborhood,
        city: createExpenseFormData.city,
        state: createExpenseFormData.state,
        complement: createExpenseFormData.complement || '',
      },
    }
  }
}
