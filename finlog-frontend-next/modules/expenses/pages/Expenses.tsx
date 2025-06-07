'use client'

import { Divider } from '@mui/material'
import { ExpensesTable } from '../components/ExpensesTable/ExpensesTable'
import { BaseInput } from '@/common/components/BaseInput/BaseInput'
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline'
import { BaseButton } from '@/common/components/BaseButton/BaseButton'
import { MainPagination } from '@/common/components/BasePagination/BasePagination'

export function Expenses() {
  return (
    <div className="flex flex-col justify-between w-full">
      <div className="flex flex-col gap-3">
        <div className="font-montserrat flex gap-2 items-center">
          <h2 className="text-[14px] uppercase font-medium">
            Total de despesas:{' '}
          </h2>
          <span className="text-[18px] uppercase font-medium">R$ 0,00</span>
        </div>
        <Divider />
        <div className="flex gap-2 justify-between items-center">
          <form>
            <BaseInput type="date" />
            <BaseInput type="date" />
            <button className="bg-blue-500 text-white rounded p-2">
              Buscar
            </button>
          </form>
          <BaseButton>
            <AddCircleOutlineIcon />
            Nova despesa
          </BaseButton>
        </div>
        <div>
          <ExpensesTable />
        </div>
      </div>
      <div className=" flex justify-end">
        <MainPagination
          pagination={{ last_page: 10, currentPage: 1 }}
          setPaginationData={() => {}}
        />
      </div>
    </div>
  )
}
